(ns advent-2019.day05
  (:gen-class)
  (:require [clojure.string :as s]
            [taoensso.tufte :refer (defnp p profile)]
            [advent-2019.core :refer [get-input parse-int-safely]]))

(def flipjoin
  "Reverse elements in a seq, join them, and parse as an integer"
  (comp #(parse-int-safely 0 %) #(s/join "" %) reverse))

(flipjoin [1 2 3])
(flipjoin "")
(->> "2"
     s/reverse
     (split-at 2)
     (map flipjoin))

(defn parse-instruction
  "Given an instruction, will return [opcode modes] with proper padding.
  For example: 102 becomes [02 001]"
  [instruction]
  (let [[op modes] (->> instruction str
                        s/reverse
                        (split-at 2)
                        (map flipjoin))
        opcode (format "%02d" op)
        padding (cond (= "03" opcode) 2
                      (= "04" opcode) 2
                      :else 3)]
    #_(println "opcode" opcode "modes" modes "padding" padding)
    [opcode (format (str "%0" padding "d") modes)]))

(parse-instruction "2")

(defn calc
  "Reads the instruction at index idx; returns the resulting program"
  [program idx]
  (let [instruction (first (drop idx program))
        [opcode modes] (->> instruction str
                            s/reverse
                            (split-at 2)
                            (map flipjoin))]
    (println "instruction:" instruction)
    (println "opcode:" opcode)
    (println "modes:" modes)))

(calc [2 255 1 255] 0)

(defn part1
  "After providing 1 to the only input instruction and passing all the tests,
  what diagnostic code does the program produce?"
  [input]
  input)

(defn part2 [input] nil)

(defn -main []
  (let [input (->> "day05.txt" get-input first (#(clojure.string/split % #",")) (map parse-int-safely) vec)]
    (time (println "Day 05, Part 1:" (part1 input)))
    (time (println "Day 05, Part 2:" (part2 input)))))

(-main)
