(ns advent-2019.day04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day04 :refer [get-digits valid-number? contains-double-digits?]]))

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

(deftest day04-part2-test
  (testing "Double Digits"
    (is (= false (contains-double-digits? (get-digits 123789))))
    (is (= false (contains-double-digits? (get-digits 111789))))
    (is (= false (contains-double-digits? (get-digits 123444))))
    (is (= false (contains-double-digits? (get-digits 121212))))
    (is (= true (contains-double-digits? (get-digits 111122))))
    (is (= true (contains-double-digits? (get-digits 112233))))))
