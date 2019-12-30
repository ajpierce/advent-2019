(ns advent-2019.day05
  (:gen-class)
  (:require [clojure.string :as s]
            [advent-2019.core :refer [get-input parse-int]]))

(defn num-params [opcode]
  (cond (= "03" opcode) 1
        (= "04" opcode) 1
        :else 3))

(def flipjoin
  "Reverse elements in a seq, and join them as a string"
  (comp #(s/join "" %) reverse))

(defn pad
  "Pads coll to length n with v"
  [n v coll]
  (take n (concat coll (repeat v))))

(defn parse-instruction
  "Given an instruction, will return [opcode modes] with proper padding.
  For example: 102 becomes [02 100]"
  [instruction]
  (let [[opcode raw-modes] (->> instruction str s/reverse
                                (pad 5 \0)
                                (split-at 2)
                                (map flipjoin))
        padding (num-params opcode)
        modes (->> raw-modes reverse (take padding) (s/join ""))]
    [opcode modes]))

(defn get-val [program [idx mode]]
  (if (= \1 mode)
    idx
    (parse-int (nth program (int idx)))))

(defn calc
  "Read the instruction at index idx. Perform the instruction.
  Return a vector of [new-program, new-index]"
  ([program]
   (calc program 0 1))
  ([program idx]
   (calc program idx 1))
  ([program idx input]
   (let [instruction (first (drop idx program))
         [opcode modes] (parse-instruction instruction)]
     (if (= "99" opcode) nil
         (let [params (->> program
                           (drop (inc idx))
                           (split-at (count modes))
                           first
                           (map parse-int))
               values (map #(get-val program %) (partition 2 (interleave params modes)))
               output (last params)
               next-idx (inc (count modes))
               next-prog (case opcode
                           "01" (assoc program output (apply + (butlast values)))
                           "02" (assoc program output (apply * (butlast values)))
                           "03" (assoc program output input)
                           "04" (do (println (last values)) program)
                           nil)]
           [next-prog next-idx])))))

(defn part1
  "After providing 1 to the only input instruction and passing all the tests,
  what diagnostic code does the program produce?"
  [initial-program]
  (loop [program initial-program idx 0]
    (let [[p i] (calc program idx)]
      (if p
        (recur p (+ idx i))
        p))))

(defn part2 [input] nil)

(defn -main []
  (let [input (->> "day05.txt" get-input first (#(clojure.string/split % #",")) vec)]
    (time (println "Day 05, Part 1:" (part1 input)))
    #_(time (println "Day 05, Part 2:" (part2 input)))))

(-main)
