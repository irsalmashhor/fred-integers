(defproject fred-integers "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.3.1"]
                 [ring-json-response "0.2.0"]
                 [org.clojure/data.json "0.2.4"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler fred-integers.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
