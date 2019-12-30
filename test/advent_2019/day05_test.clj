(ns advent-2019.day05-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day05 :refer [calc parse-instruction run]]))

(deftest day05
  (testing "instruction parsing"
    (is (= ["02" "000"] (parse-instruction 2)))
    (is (= ["01" "100"] (parse-instruction 101)))
    (is (= ["01" "001"] (parse-instruction 10001)))
    (is (= ["11" "000"] (parse-instruction 11)))
    (is (= ["03" "0"] (parse-instruction 3)))
    (is (= ["03" "0"] (parse-instruction "03")))
    (is (= ["03" "1"] (parse-instruction 103)))
    (is (= ["02" "101"] (parse-instruction 10102)))
    (is (= ["01" "111"] (parse-instruction 11101)))
    (is (= ["01" "010"] (parse-instruction 1001)))
    (is (= ["13" "000"] (parse-instruction 13))))

  (testing "Day 05 Opcodes"
    (is (= [1002 4 3 4 99] (first (calc [1002 4 3 4 33]))))
    (is (= [1101 100 -1 4 99] (first (calc [1101 100 -1 4 0])))))

  (testing "Day 02 Opcodes"
    (is (= [2 0 0 0 99] (first (calc [1 0 0 0 99]))))
    (is (= [1 0 0 2 99] (first (calc [1 0 0 3 99]))))
    (is (= [2 3 0 6 99] (first (calc [2 3 0 3 99]))))
    (is (= [2 4 4 5 99 9801] (first (calc [2 4 4 5 99 0]))))))
