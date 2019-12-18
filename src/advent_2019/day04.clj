(ns advent-2019.day04
  (:gen-class)
  (:require
   [clojure.core.reducers :as r]
   [advent-2019.core :refer [get-input]]))

(defn get-digits
  "Transforms a string or a number into a list of individual digits"
  [number] (map #(Character/digit % 10) (str number)))

(defn contains-repeat-digit? [digits]
  (->> digits
       (partition-by identity)
       (filter (fn [x] (> (count x) 1)))
       empty?
       not))

(defn valid-digits? [digits]
  (and (apply <= digits)
       (contains-repeat-digit? digits)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Old, naive implementation ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn valid-number? [number]
  (let [digits (get-digits number)]
    (valid-digits? digits)))

(defn part1-naive
  "This one ended up taking ~6.5s on my computer vs. the 1.2s it ended up being with reducers"
  [[start end]]
  (count (filter valid-number? (range start (inc end)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn contains-double-digits? [digits]
  (->> digits
       (partition-by identity)
       (filter (fn [x] (= (count x) 2)))
       empty?
       not))

(defn valid-digits??
  "An Elf just remembered one more important detail:
  The two adjacent matching digits are not part of a larger group of matching digits."
  [digits]
  (and (apply <= digits)
       (contains-double-digits? digits)))

(defn part1 [digits]
  (->> digits (r/filter valid-digits?) (r/foldcat) (count)))

(defn part2 [digits]
  (->> digits (r/filter valid-digits??) (r/foldcat) (count)))

(defn -main []
  (let [[start end] (->> "day04.txt" get-input first
                         (#(clojure.string/split % #"-"))
                         (map #(Integer/parseInt %)))
        numbers (vec (range start (inc end)))
        digits (->> numbers (r/map get-digits))]
    ;(time (println "Day 04, Part 1, naive" (part1-naive [start end])))
    (time (println "Day 04, Part 1:" (part1 digits)))
    (time (println "Day 04, Part 2:" (part2 digits)))))
