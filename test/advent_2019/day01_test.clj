(ns advent-2019.day01-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day01 :refer [calc-fuel calc-total-fuel]]))

(deftest calc-fuel-test
  (testing "Fuel requirements for a mass of 12"
    (is (= 2 (calc-fuel 12))))
  (testing "Fuel requirements for a mass of 14"
    (is (= 2 (calc-fuel 14))))
  (testing "Fuel requirements for a mass of 1969"
    (is (= 654 (calc-fuel 1969))))
  (testing "Fuel requirements for a mass of 100756"
    (is (= 33583 (calc-fuel 100756)))))

(deftest total-fuel-test
  (testing "TOTAL fuel requirements for a mass of 12"
    (is (= 2 (calc-total-fuel 12))))
  (testing "TOTAL fuel requirements for a mass of 1969"
    (is (= 966 (calc-total-fuel 1969))))
  (testing "TOTAL fuel requirements for a mass of 100756"
    (is (= 50346 (calc-total-fuel 100756)))))
