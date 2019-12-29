(ns advent-2019.day04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day04 :refer [get-digits valid-digits? contains-double-digits?]]))

(deftest day04-part1-test
  (testing "Digits"
    (is (= '(1 2 3 4) (get-digits 1234)))
    (is (= '(1 2 3 4) (get-digits "1234"))))
  (testing "Valid Numbers"
    (is (= true (valid-digits? (get-digits 111111))))
    (is (= true (valid-digits? (get-digits 122345))))
    (is (= true (valid-digits? (get-digits 111123)))))
  (testing "Invalid Numbers"
    (is (= false (valid-digits? (get-digits 223450))))
    (is (= false (valid-digits? (get-digits 223554))))
    (is (= false (valid-digits? (get-digits 554321))))
    (is (= false (valid-digits? (get-digits 987766))))
    (is (= false (valid-digits? (get-digits 123789))))))

(deftest day04-part2-test
  (testing "Double Digits"
    (is (= false (contains-double-digits? (get-digits 123789))))
    (is (= false (contains-double-digits? (get-digits 111789))))
    (is (= false (contains-double-digits? (get-digits 123444))))
    (is (= false (contains-double-digits? (get-digits 121212))))
    (is (= true (contains-double-digits? (get-digits 111122))))
    (is (= true (contains-double-digits? (get-digits 112233))))))
