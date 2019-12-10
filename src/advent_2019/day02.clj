(ns advent-2019.day02
  (:gen-class)
  (:require [advent-2019.core :refer [get-input]]))

(defn execute
  [intcode i op]
  (let [[_ p1 p2 o] (take 4 (drop i intcode))]
    (assoc intcode o (op (nth intcode p1) (nth intcode p2)))))

(defn calc
  " To run one, start by looking at the first integer (called position 0). Here, you will find an opcode - either 1, 2, or 99. The opcode indicates what to do; for example, 99 means that the program is finished and should immediately halt. Encountering an unknown opcode means something went wrong."
  [input]
  (loop [intcode input i 0]
    (let [op (nth intcode i)]
      (case op
        99 intcode
        1 (recur (execute intcode i +) (+ i 4))
        2 (recur (execute intcode i *) (+ i 4))
        intcode))))

(execute [1 0 0 0 99] 0 +)

(take 4 (drop 0 [1 0 0 0 99]))

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
