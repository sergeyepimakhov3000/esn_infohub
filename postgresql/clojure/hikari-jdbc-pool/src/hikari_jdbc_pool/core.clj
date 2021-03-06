(ns hikari-jdbc-pool.core
  (:require [hikari-cp.core :as hi]
            [clojure.java.jdbc :as jdbc]))

(def datasource-options {:adapter            "postgresql"
                         :username           "postgres"
                         :password           "postgres"
                         :database-name      "test"
                         :server-name        "localhost"
                         :port-number        5432})

(defonce datasource
         (delay (hi/make-datasource datasource-options)))

(defn -main [& args]
  (jdbc/with-db-connection [conn {:datasource @datasource}]
                           (let [rows (jdbc/query conn "SELECT 0")]
                             (println rows)))
  (hi/close-datasource @datasource))
