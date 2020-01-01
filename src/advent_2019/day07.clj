(ns advent-2019.day07
  (:gen-class)
  (:require [clojure.string :as s]
            [clojure.math.combinatorics :as combo]
            [advent-2019.intcode :as i]
            [advent-2019.core :refer [get-input print->]]))

(defn part1
  "Try every combination of phase settings on the amplifiers.
  What is the highest signal that can be sent to the thrusters?"
  [program] nil)

(defn part2
  [program] nil)

(def combos (combo/permutations [0 1 2 3 4]))

(defn amp-chain
  "Given an incode program and 5-digit phase sequence, chain program output through
  all five amps as second argument following the corresponding phase sequence"
  [program [a b c d e]]
  (->> (i/run program (list a 0))
       #_(print-> "Phase 1 output:")
       (#(i/run program (list b %)))
       #_(print-> "Phase 2 output:")
       (#(i/run program (list c %)))
       #_(print-> "Phase 3 output:")
       (#(i/run program (list d %)))
       #_(print-> "Phase 4 output:")
       (#(i/run program (list e %)))
       #_(print-> "Phase 5 output:")))

#_(def input (->> "day07.txt" get-input first (#(clojure.string/split % #",")) vec))

(defn -main []
  (let [input (->> "day07.txt" get-input first (#(clojure.string/split % #",")) vec)]
    [(time (print-> "Day 07, Part 1:" (part1 input)))
     (time (print-> "Day 07, Part 2:" (part2 input)))]))

#_(-main)
