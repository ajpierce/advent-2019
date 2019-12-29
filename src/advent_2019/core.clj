(ns advent-2019.core
  (:require [taoensso.tufte :as tufte]))

;; Set up profiling for the entire project
(tufte/add-basic-println-handler!
 {:format-pstats-opts {:columns [:n-calls :p50 :p90 :mean :mad :clock :total]}})

(defn parse-int-or-nil
  "Will attempt to parse a string as an Integer; will return nil if it fails"
  [^String s]
  (try (Integer/parseInt s)
       (catch Exception e
         (str "Failed to parse int: " (.getMessage e))
         nil)))

(defn parse-int
  "Given a string, returns an integer representation"
  [^String x]
  (Integer/parseInt x))

(defn get-input
  "Given the name of a file in the resources folder, parse it and return a vec of the lines in the file.
  If the second argument is true, will cast each line to int (nil if parsing fails)

  NOTE: We must manually prefix 'resources/' and use io.reader instead of io.resource
  due to limitations of GraalVM compilation"
  ([filename]
   (get-input filename false))
  ([filename parseInt?]
   (let [raw-input (-> (str "resources/" filename)
                       (clojure.java.io/reader)
                       (slurp)
                       (clojure.string/split-lines))]
     (if parseInt?
       (map parse-int-or-nil raw-input)
       raw-input))))
