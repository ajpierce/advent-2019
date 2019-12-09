(ns advent-2019.day02-test
  (:require [clojure.test :refer :all]
            [advent-2019.day02 :refer :all]))

(deftest opcodes
  (testing "Opcode 1"
    (is (= [2 0 0 0 99] (op1 [1 0 0 0 99])))
    (is (= [1 0 0 2 99] (op1 [1 0 0 3 99]))))
  (testing "Opcode 2"
    (is (= [2 3 0 3 99] (op2 [2 3 0 6 99])))
    (is (= [2 4 4 5 99 0] (op2 [2 4 4 5 99 9801]))))
  (testing "Multi-op computation"
    (is (= [30 1 1 4 2 5 6 0 99] (calc [1 1 1 4 99 5 6 0 99])))
    (is (= [3500 9 10 70 2 3 11 0 99 30 40 50] (calc [1 9 10 3 2 3 11 0 99 30 40 50])))))
