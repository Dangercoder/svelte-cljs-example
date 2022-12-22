(ns +page.server
  (:require ["@sveltejs/kit" :refer [invalid redirect]]
            ["../../lib/api.js" :refer [post]]))

(defn ^:async load [{:keys [cookies]}]
  (when (.get cookies "jwt")
    (throw (redirect 307 "/"))))

(defn set-cookie-and-redirect [value cookies]
  (.set cookies :jwt value {:path "/"})
  (throw (redirect 307 "/")))

(defn ^:async login [{:keys [request cookies]}]
  (let [data (-> request
                 (.formData)
                 js/await)
        result (-> {:path "users/login"
                    :data {:user {:email (.get data "email")
                                  :password (.get data "password")}}}
                   post
                   js/await)]
    (if (.-errors result)
      (invalid 401 result)
      (-> result
          (.-user)
          js/JSON.stringify
          js/btoa
          (set-cookie-and-redirect cookies)))))

;; Form Actions
(def actions {:default login})