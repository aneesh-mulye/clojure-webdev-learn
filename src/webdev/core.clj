(ns webdev.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn greet [req]
  {:status 200
   :body "Hello, Duniya!"
   :headers {}})

(defn part [req]
  {:status 200
   :body "Goodbye, cruel world!"
   :headers {}})

(defroutes app
  (GET "/" [] greet)
  (GET "/goodbye" [] part)
  (not-found "Page not found"))

(defn -main [port]
  (jetty/run-jetty
    app
    {:port (Integer. port)}))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port)}))
