(ns advent-2019.day02
  (:gen-class)
  (:require [advent-2019.core :refer [get-input]]))

(defn op1
  "Adds together numbers read from two positions and stores the result in a third position.
  The three integers immediately after the opcode tell you these three positions -
  the first two indicate the positions from which you should read the input values,
  and the third indicates the position at which the output should be stored."
  [x]
  x)

(defn op2
  "Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them."
  [x]
  x)

(defn calc
  " To run one, start by looking at the first integer (called position 0). Here, you will find an opcode - either 1, 2, or 99. The opcode indicates what to do; for example, 99 means that the program is finished and should immediately halt. Encountering an unknown opcode means something went wrong."
  [x]
  x)

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
