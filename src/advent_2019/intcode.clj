(ns advent-2019.intcode
  (:gen-class)
  (:require [clojure.string :as s]
            [advent-2019.core :refer [parse-int]]))

(def flipjoin
  "Reverse elements in a seq, and join them as a string"
  (comp #(s/join "" %) reverse))

(defn pad
  "Pads coll to length n with v"
  [n v coll]
  (take n (concat coll (repeat v))))

(defn num-params [opcode]
  (cond (= "03" opcode) 1
        (= "04" opcode) 1
        (= "05" opcode) 2
        (= "06" opcode) 2
        :else 3))

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

(defn get-val
  "Handles position vs immediate mode for parameters"
  [program [idx mode]]
  (if (= \1 mode)
    idx
    (parse-int (nth program (int idx)))))

(defn calc
  "Read the instruction at index idx. Perform the instruction.
  Return a vector of [next-program, next-index, next-outputs] if incomplete,
  or the outputs if complete."
  ([program]
   (calc program 0 [] '(1)))
  ([program idx]
   (calc program idx [] '(1)))
  ([program idx outputs]
   (calc program idx outputs '(1)))
  ([program idx outputs inputs]
   (let [instruction (first (drop idx program))
         [opcode modes] (parse-instruction instruction)]
     (if (= "99" opcode) [nil nil outputs]
         (let [params (->> program (drop (inc idx))
                           (split-at (count modes))
                           first
                           (map parse-int))
               values (->> modes (interleave params)
                           (partition 2)
                           (map #(get-val program %)))
               output (last params)
               next-idx (cond (and (= "05" opcode)
                                   (not (zero? (first values)))) (second values)
                              (and (= "06" opcode)
                                   (zero? (first values))) (second values)
                              :else (+ idx (inc (count modes))))
               next-prog (case opcode
                           "01" (assoc program output (apply + (butlast values)))
                           "02" (assoc program output (apply * (butlast values)))
                           "03" (assoc program output (peek inputs))
                           "07" (if (< (first values) (second values))
                                  (assoc program output 1)
                                  (assoc program output 0))
                           "08" (if (= (first values) (second values))
                                  (assoc program output 1)
                                  (assoc program output 0))
                           program)
               next-outputs (case opcode
                              "04" (conj outputs (last values))
                              outputs)
               next-inputs (if (= "03" opcode) (pop inputs) inputs)]
           [next-prog next-idx next-outputs next-inputs])))))

(defn run
  "Executes the program until a stop code (99) is reached.
  Returns the last value on the output stack"
  ([initial-program]
   (run initial-program '(1)))
  ([initial-program input-values]
   (loop [program initial-program
          idx 0
          outputs []
          inputs (if-not (seq? input-values) (list input-values) input-values)]
     (let [[p i o ri] (calc program idx outputs inputs)]
       (if p
         (recur p i o ri)
         (last o))))))
