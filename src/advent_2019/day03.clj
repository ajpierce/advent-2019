(ns advent-2019.day03
  (:gen-class)
  (:require [clojure.set :refer [intersection]]
            [advent-2019.core :refer [get-input]]))

(defn parse-segment
  ([segment] (parse-segment segment [0 0]))
  ([segment start]
   (let [direction (subs segment 0 1)
         length (Integer/parseInt (subs segment 1))
         [x y] start
         [dx dy] (case direction
                   "U" [0 1]
                   "D" [0 -1]
                   "R" [1 0]
                   "L" [-1 0])]
     (vec (for [i (map inc (range length))]
            [(+ x (* dx i)) (+ y (* dy i))])))))

(defn build-circuit [wire]
  (loop [circuit [[0 0]]
         remaining wire]
    (if (empty? remaining)
      (into (sorted-set) (rest circuit))
      (let [pos (last circuit)
            segment (first remaining)
            path (parse-segment segment pos)]
        (recur (into circuit path) (rest remaining))))))

(defn calc-manhattan [[x y]] (+ (Math/abs x) (Math/abs y)))

(defn part1
  "To fix the circuit, you need to find the intersection point closest to the central port.
  What is the Manhattan distance from the central port to the closest intersection?"
  [[wire1 wire2]]
  (let [c1 (build-circuit wire1)
        c2 (build-circuit wire2)
        intersections (clojure.set/intersection c1 c2)]
    (apply min (map calc-manhattan intersections))))

(defn part2
  ""
  [input]
  input)

(defn -main []
  (let [input (map #(clojure.string/split % #",") (get-input "day03.txt"))]
    (println "Day 03, Part 1:" (part1 input))
    (println "Day 03, Part 2:" (part2 input))))

(parse-seg "U5")
