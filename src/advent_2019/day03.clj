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
      (rest circuit)
      (let [pos (last circuit)
            segment (first remaining)
            path (parse-segment segment pos)]
        (recur (into circuit path) (rest remaining))))))

(defn calc-manhattan [[^Integer x ^Integer y]] (+ (Math/abs x) (Math/abs y)))

(defn calc-circuit-size [circuit]
  (->> (map vector circuit (iterate inc 1))
       (reverse)
       (into {})))

(defn calc-intersections [circuit1 circuit2]
  (intersection (into #{} circuit1) (into #{} circuit2)))

(defn solve-p1
  "To fix the circuit, you need to find the intersection point closest to the central port.
  What is the Manhattan distance from the central port to the closest intersection?"
  [intersections]
  (apply min (map calc-manhattan intersections)))

(defn solve-p2
  "What is the fewest combined steps the wires must take to reach an intersection?"
  [c1 c2 intersections]
  (let [s1 (calc-circuit-size c1)
        s2 (calc-circuit-size c2)]
    (->> intersections
         (map (fn [i] (+ (get s1 i) (get s2 i))))
         (apply min))))

(defn part1
  "Includes the circuit-building step before solving (for unit tests).
   We omit this step in -main to avoid processing data twice"
  [[wire1 wire2]]
  (let [c1 (build-circuit wire1)
        c2 (build-circuit wire2)
        intersections (calc-intersections c1 c2)]
    (solve-p1 intersections)))

(defn part2
  "Includes the circuit-building step before solving (for unit tests).
   We omit this step in -main to avoid processing data twice"
  [[wire1 wire2]]
  (let [c1 (build-circuit wire1)
        c2 (build-circuit wire2)
        intersections (calc-intersections c1 c2)]
    (solve-p2 c1 c2 intersections)))

(defn -main []
  (let [[wire1 wire2] (map #(clojure.string/split % #",") (get-input "day03.txt"))
        c1 (build-circuit wire1)
        c2 (build-circuit wire2)
        intersections (calc-intersections c1 c2)]
    (time (println "Day 03, Part 1:" (solve-p1 intersections)))
    (time (println "Day 03, Part 2:" (solve-p2 c1 c2 intersections)))))
