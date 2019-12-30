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
  Return a vector of [next-program, next-index, next-outputs] if incomplete,
  or the outputs if complete."
  ([program]
   (calc program 0 [] 1))
  ([program idx]
   (calc program idx [] 1))
  ([program idx outputs]
   (calc program idx outputs 1))
  ([program idx outputs input]
   (let [instruction (first (drop idx program))
         [opcode modes] (parse-instruction instruction)]
     (if (= "99" opcode) [nil nil outputs]
         (let [params (->> program (drop (inc idx))
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
                           program)
               next-outputs (case opcode
                              "04" (conj outputs (last values))
                              outputs)]
           [next-prog next-idx next-outputs])))))

(defn run
  "Executes the program until a stop code (99) is reached.
  Returns the last value on the output stack"
  ([initial-program]
   (run 1))
  ([initial-program input]
   (loop [program initial-program
          idx 0
          outputs []]
     (let [[p i o] (calc program idx outputs input)]
       (if p
         (recur p i o)
         (last o))))))

(defn part1
  "After providing 1 to the only input instruction and passing all the tests,
  what diagnostic code does the program produce?"
  [input] (run input 1))

(defn part2
  "What is the diagnostic code for system ID 5?"
  [input] (run input 5))

(defn -main []
  (let [input (->> "day05.txt" get-input first (#(clojure.string/split % #",")) vec)]
    (time (println "Day 05, Part 1:" (part1 input)))
    (time (println "Day 05, Part 2:" (part2 input)))))
