(ns tasks
  (:require
   [babashka.tasks :refer [shell]]
   [babashka.fs :as fs]
   [clojure.string :as str]))

(defn compile-squint
  "Compiles all squint files to .js"
  [{:keys []}]
  (println "Compiling squint files")
  (doseq [path (fs/glob "src" "**{.cljs,cljc}")]
    (shell (format "node node_modules/.bin/squint compile %s --extension .js" path))))

(defn watch-cljs [{:keys []}]
  (let [watch (requiring-resolve 'pod.babashka.fswatcher/watch)]
    (watch "src"
           (fn [{:keys [type path]}]
             (when
              (and (#{:write :write|chmod} type)
                   (or (str/ends-with? path ".cljs")
                       (str/ends-with? path ".cljc")))
               (shell {:continue true} (format "node node_modules/.bin/squint compile %s --extension .js" path))))
           {:recursive true})
    @(promise)))