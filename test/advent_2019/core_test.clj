(ns advent-2019.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.core :refer [get-input]]))

(deftest get-input-tests
  (testing "get-input function"
    (testing "without integer parsing"
      (let [input (get-input "test.txt")]
        (is (= "12345" (first input)))
        (is (= "01234" (second input)))
        (is (= "This is not an integer lol" (last input)))))
    (testing "with integer parsing"
      (let [input (get-input "test.txt" true)]
        (is (= 12345 (first input)))
        (is (= 1234 (second input)))
        (is (= nil (last input)))))))
