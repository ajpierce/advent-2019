(ns advent-2019.day04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day04 :refer [get-digits valid-number?]]))

(deftest day04-part1-test
  (testing "Digits"
    (is (= '(1 2 3 4) (get-digits 1234)))
    (is (= '(1 2 3 4) (get-digits "1234"))))
  (testing "Valid Numbers"
    (is (= true (valid-number? 111111)))
    (is (= true (valid-number? 122345)))
    (is (= true (valid-number? 111123))))
  (testing "Invalid Numbers"
    (is (= false (valid-number? 223450)))
    (is (= false (valid-number? 223554)))
    (is (= false (valid-number? 554321)))
    (is (= false (valid-number? 987766)))
    (is (= false (valid-number? 123789)))))
