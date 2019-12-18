(ns advent-2019.day04
  (:gen-class)
  (:require
   [clojure.core.reducers :as r]
   [advent-2019.core :refer [get-input]]))

(defn get-digits
  "Transforms a string or a number into a list of individual digits"
  [number] (map #(Character/digit % 10) (str number)))

(defn valid-number? [number]
  (let [digits (get-digits number)]
    (and (not (apply distinct? digits))
         (apply <= digits))))

(defn part1-naive [[start end]]
  (count (filter valid-number? (range start (inc end)))))

(defn part1-parallel [[start end]]
  (->> (range start (inc end))
       vec ; Reducers work 5x faster on vectors
       (r/filter valid-number?)
       (r/foldcat)
       (count)))

(defn part2 [[start end]]
  (str start " to " end))

(defn -main []
  (let [input (->> "day04.txt"
                   get-input
                   first
                   (#(clojure.string/split % #"-"))
                   (map #(Integer/parseInt %)))]
    (time (println "Day 04, Part 1, faster" (part1-parallel input)))))
    ;(println "Day 04, Part 2:" (part2 input))))
