(ns advent-2019.core)

(defn parse-int-or-nil
  "Will attempt to parse a string as an Integer; will return nil if it fails"
  [s]
  (try (Integer/parseInt s)
       (catch Exception e
         (str "Failed to parse int: " (.getMessage e))
         nil)))

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
