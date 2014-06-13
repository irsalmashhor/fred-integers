(ns fred-integers.handler
  (:use compojure.core
        ring.util.response
        [ring.middleware.json-response :only [wrap-json-response]]
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.json :only [wrap-json-params]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.data.json :as json]))


(defn average [xs]
  (/ (reduce + xs) (count xs)))

(defn map-overall-average [m]
  (if m
    (average (map average (vals m)))
    nil))

(defn map-key-average [k m]
  (average (m k)))

(defn map-overall-average-handler [m] 
  (response {:average (map-overall-average m)} ))

(defn map-key-average-handler [k m] 
  (response {:average (map-key-average k m)} ))


(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/map-average" [m] (map-overall-average-handler (json/read-str m)))
  (GET "/map-average/:k" [k m]  (map-key-average-handler k (json/read-str m)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-json-response
  (wrap-params
  (handler/site app-routes))))
