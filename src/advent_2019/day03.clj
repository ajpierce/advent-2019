(ns advent-2019.day03
  (:gen-class)
  (:require [advent-2019.core :refer [get-input]]))

(defn part1
  "To fix the circuit, you need to find the intersection point closest to the central port.
  What is the Manhattan distance from the central port to the closest intersection?"
  [input]
  input)

(defn part2
  ""
  [input]
  input)

(defn -main []
  (let [input (map #(clojure.string/split % #",") (get-input "day03.txt"))]
    (println "Day 03, Part 1:" (part1 input))
    (println "Day 03, Part 2:" (part2 input))))
