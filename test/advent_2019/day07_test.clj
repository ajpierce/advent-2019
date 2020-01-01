(ns advent-2019.day07-test
  (:require [clojure.test :as t]
            [clojure.string :as s]
            [advent-2019.day07 :refer [amp-chain]]))

(def p1 (-> "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"
            (clojure.string/split #",") vec))
(def p2 (-> "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"
            (clojure.string/split #",") vec))
(def p3 (-> "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
            (clojure.string/split #",") vec))

(t/deftest day07-tests
  (t/testing "amp-chain"
    (t/is (= 43210 (amp-chain p1 [4 3 2 1 0])))
    (t/is (= 54321 (amp-chain p2 [0 1 2 3 4])))
    (t/is (= 65210 (amp-chain p3 [1 0 4 3 2])))))
