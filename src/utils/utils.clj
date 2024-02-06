(ns utils.utils
  (:require [clojure.spec.alpha :as s]
            [clojure.pprint :as pprint]
            [cheshire.core :as json]
            [clojure.java.io :as io]))

(defn with-error-handling [f & args]
  (try
    (apply f args)
    (catch Exception e
      (println "An error occurred during processing:")
      (.printStackTrace e)
      nil)))  ;; Return nil or handle the error as needed

(defn validate-against-spec [spec object]
  (if (s/valid? spec object)
    object
    (let [errors (s/explain-data spec object)]
      (println "Validation errors:")
      (println (s/explain-str spec object))
      (throw (ex-info "Invalid format" {:errors errors})))))


(defn print&forward [data]
  (println "P&F:")
  (pprint/pprint data)
  data)

;; Function to read JSON file and parse it into a Clojure map
(defn read-json-file [filename]
  (with-open [rdr (io/reader filename)]
    (json/parse-stream rdr true)))

(defn local-date-to-instant [local-date]
  (let [start-of-day (java.time.ZonedDateTime/of local-date
                                                 java.time.LocalTime/MIDNIGHT
                                                 (java.time.ZoneId/of "UTC"))]
    (.toInstant start-of-day)))