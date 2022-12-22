(ns +page.server
  (:require ["@sveltejs/kit" :refer [fail redirect]]
            ["../../lib/api.js" :refer [post]])
  (:require-macros [macros :refer [btoa json-stringify]]))

(js* "/** @type {import('./$types').PageServerLoad} */")
(defn ^:async load [{:js/keys [parent]}]
  (when (js/await parent)
    (throw (redirect 307 "/"))))

(defn set-cookie-and-return [value cookies]
  (.set cookies :jwt value {:path "/"})
  (throw (redirect 307 "/")))

(defn ^:async register [event]
  (let [request (:request event)
        cookies (:cookies event)
        data (-> request
                 (.formData)
                 js/await)
        body (-> (post {:path "users"
                        :data {:user {:email (.get data "email")
                                      :username (.get data "username")
                                      :password (.get data "password")}}})
                 js/await)
]
    (if (.-errors body)
      (fail 401 body)
      (-> body
          (.-user)
          js/JSON.stringify
          js/btoa
          (set-cookie-and-return cookies)))))

;; form actions
(def actions {:default register})