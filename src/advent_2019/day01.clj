(ns advent-2019.day01
  (:require [advent-2019.core :refer [get-input]]))

(defn calc-fuel
  "Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2."
  [mass]
  (-> mass (/ 3) (Math/floor) (int) (- 2)))

(defn calc-total-fuel
  "For each module mass, calculate its fuel and add it to the total. Then, treat the fuel amount you just calculated as the input mass and repeat the process, continuing until a fuel requirement is zero or negative."
  [mass]
  (loop [masses [] m mass]
    (let [fuel (calc-fuel m)]
      (if (< fuel 1)
        (reduce + masses)
        (recur (conj masses fuel) fuel)))))

(def input (get-input "day01.txt" true))  ; Passing true converts input strings to ints

; Part 1 Solution
(comment "What is the sum of the fuel requirements for all of the modules on your spacecraft?")
(->> input
     (map calc-fuel)
     (reduce +))

; Part 2 Solution
(comment "What is the sum of the fuel requirements for all of the modules on your spacecraft when also taking into account the mass of the added fuel?")
(->> input
     (map calc-total-fuel)
     (reduce +))
