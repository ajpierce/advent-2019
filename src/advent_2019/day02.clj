(ns advent-2019.day02
  (:gen-class)
  (:require [advent-2019.core :refer [get-input]]))

(defn calc
  [input]
  (loop [intcode input i 0]
      (let [[opcode p1 p2 o & codes] (drop i intcode)
            op (case opcode
                1 +
                2 *
                nil)]
        (if op
          (recur (assoc intcode o (op (nth intcode p1) (nth intcode p2))) (+ i 4))
          intcode))))

(defn part1
  "[Take puzzle input and] replace position 1 with the value 12 and replace position 2 with the value 2.
  What value is left at position 0 after the program halts?"
  [input]
  (->> input))

(defn part2
  ""
  [input]
  (->> input))

(defn -main
  "An Intcode program is a list of integers separated by commas (like 1,0,0,3,99)."
  []
  (let [raw-input (-> "day02.txt" get-input first (clojure.string/split #","))
        input (->> raw-input (map read-string) vec)]
    (println "Day 02, Part 1:" (part1 input))
    (println "Day 02, Part 2:" (part2 input))
    input))

(-main)
