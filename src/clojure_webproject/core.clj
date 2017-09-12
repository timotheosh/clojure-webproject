(ns clojure-webproject.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [clojure-webproject.view :as view]))

(defn view-page
  [data]
  (reduce str (view/v-page data)))

(defn default_page
  "Default response."
  [req]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (view-page
          {:title "We're sorry"
           :body "404: This is not the page you're looking for."})})

(defn index_page
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view-page
          {:title "The Root Page"
           :body "This is the main page."})})

(defn about_page
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view-page
          {:title "About Me"
           :body "Nothing much to tell..."})})

(defn handler
  [req]
  (cond
    (= "/" (:uri req)) (index_page req)
    (= "/about" (:uri req)) (about_page req)
    :else (default_page req))
  )

(def app
  (-> #'handler
      (wrap-reload '(clojure-webproject.core))
      (wrap-stacktrace)))

(defn -main
  [& args]
  (run-jetty #'app {:port 8888}))
