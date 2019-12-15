(ns advent-2019.day02-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day02 :refer [calc]]))

(deftest day02part1
  (testing "Opcodes"
    (is (= [2 0 0 0 99] (calc [1 0 0 0 99])))
    (is (= [1 0 0 2 99] (calc [1 0 0 3 99])))
    (is (= [2 3 0 6 99] (calc [2 3 0 3 99])))
    (is (= [2 4 4 5 99 9801] (calc [2 4 4 5 99 0])))
    (is (= [30 1 1 4 2 5 6 0 99] (calc [1 1 1 4 99 5 6 0 99])))
    (is (= [3500 9 10 70 2 3 11 0 99 30 40 50] (calc [1 9 10 3 2 3 11 0 99 30 40 50])))))
