(ns advent-2019.day03
  (:gen-class)
  (:require [advent-2019.core :refer [get-input]]))

(defn build-circuit [path]
  (loop [circuit (hash-map)
         remaining path]))

(defn parse-seg [seg]
  (let [direction (subs sec 0 1)
        length (Integer/parseInt (subs sec 1))]
    [direction length]))

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

(parse-seg "U5")
