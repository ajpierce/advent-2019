(ns advent-2019.day04
  (:gen-class)
  (:require [clojure.core.reducers :as r]
            [taoensso.tufte :refer (defnp p profile)]
            [advent-2019.core :refer [get-input]]))

(defn get-digit [^Character x] (Character/digit x 10))

(defn get-digits
  "Transforms a string or a number into a list of individual digits"
  [number]
  (->> number str (map get-digit)))

(defn valid-digits?  ;; Part 1 filter function
  [digits]
  (and (apply <= digits)
       (not (apply distinct? digits))))

(defn contains-double-digits?
  [digits]
  (->> digits
       (partition-by identity)
       (filter (fn [x] (= (count x) 2)))
       empty?
       not))

(defn valid-digits??  ;; Part 2 filter function
  "An Elf just remembered one more important detail:
  The two adjacent matching digits are not part of a larger group of matching digits."
  [digits]
  (and (apply <= digits)
       (contains-double-digits? digits)))

(defn part1 [digits]
  (->> digits (r/filter valid-digits?) (r/map (constantly 1)) (r/fold +)))

(defn part2 [digits]
  (->> digits (r/filter valid-digits??) (r/map (constantly 1)) (r/fold +)))

(defn -main []
  (let [[start end] (->> "day04.txt" get-input first (#(clojure.string/split % #"-")) (map #(Integer/parseInt %)))
        numbers (vec (range start (inc end)))
        digits (map get-digits numbers)]
    (println "Day 04, Part 1:" (part1 digits))
    (println "Day 04, Part 2:" (part2 digits))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; To pofile, remove #_ and eval block ;;
#_(let [numbers (range 402328 (inc 864247))
        digits (map get-digits numbers)]
    (profile {} (dotimes [_ 20]
                  (p ::part1 (part1 digits))
                  (p ::part2 (part2 digits)))))
