(ns advent-2019.intcode
  (:gen-class)
  (:require [clojure.core.async :refer [>!! <!! >! onto-chan chan go sliding-buffer]]
            [clojure.string :as s]
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
   (calc program 0))
  ([program idx]
   (calc program idx (let [in-chan (chan 1)] (>!! 1) in-chan)))
  ([program idx in-chan]
   (calc program idx in-chan (chan (sliding-buffer 1))))
  ([program idx in-chan out-chan]
   (calc program idx in-chan out-chan :default))
  ([program idx in-chan out-chan id]
   (let [instruction (first (drop idx program))
         [opcode modes] (parse-instruction instruction)]
     (if (= "99" opcode)
       [nil nil] ;; <== Terminate the prgram; it is complete
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
                         "03" (assoc program output (do (println "reading from input channel" id)
                                                        (<!! in-chan)))
                         "07" (if (< (first values) (second values))
                                (assoc program output 1)
                                (assoc program output 0))
                         "08" (if (= (first values) (second values))
                                (assoc program output 1)
                                (assoc program output 0))
                         program)]
         (when (= "04" opcode) (go (>! out-chan (last values))))
         [next-prog next-idx])))))

(defn input-to-chan
  "Takes input and coerces it into a chan if it's not one already"
  [input]
  (if (instance? clojure.core.async.impl.channels.ManyToManyChannel input)
    input ;; Already a chan, just return it
    (let [input-coll (if (coll? input) input (list input))
          c (chan (count input-coll))]
      (do (onto-chan c input-coll false) c))))

(defn run
  "Executes the program until a stop code (99) is reached.
  Returns the last value on the output stack"
  ([initial-program]
   (run initial-program '(1)))
  ([initial-program input-values]
   (run initial-program input-values (chan (sliding-buffer 1))))
  ([initial-program input-values out-chan]
   (run initial-program input-values out-chan identity))
  ([initial-program input-values out-chan callback]
   (run initial-program input-values out-chan callback :default))
  ([initial-program input-values out-chan callback id]
   (let [in-chan (input-to-chan input-values)]
     (loop [program initial-program
            idx 0]
       (let [[p i] (calc program idx in-chan out-chan id)]
         (if p
           (recur p i)
           (do (println "Program" id "is complete; popping output off the channel")
               (callback (<!! out-chan)))))))))
