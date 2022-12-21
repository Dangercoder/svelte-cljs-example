(ns routes.home.script
  #_{:clj-kondo/ignore [:unused-namespace]}
  (:require ["../components/Nested.svelte$default" :as Nested])
  (:require-macros [macros :refer [defreactive]]))

(def cats [])

(defreactive answer (* 2 (count cats)))

(defn addCat []
  (let [new-cat-id (+ 1 (count cats))
        new-cat {:id 1 :name "Danger Cat"}]
    (set! cats (conj! cats new-cat))))