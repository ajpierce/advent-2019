(ns advent-2019.day05
  (:gen-class)
  (:require [clojure.string :as s]
            [advent-2019.intcode :as i]
            [advent-2019.core :refer [get-input parse-int]]))

(defn part1
  "After providing 1 to the only input instruction and passing all the tests,
  what diagnostic code does the program produce?"
  [program] (i/run program 1))

(defn part2
  "What is the diagnostic code for system ID 5?"
  [program] (i/run program 5))

(defn -main []
  (let [input (->> "day05.txt" get-input first (#(clojure.string/split % #",")) vec)]
    (time (println "Day 05, Part 1:" (part1 input)))
    (time (println "Day 05, Part 2:" (part2 input)))))
