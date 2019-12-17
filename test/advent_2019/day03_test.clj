(ns advent-2019.day03-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day03 :refer [parse-segment build-circuit part1 part2 calc-circuit-size]]))

(deftest day03tests
  (testing "Segment Parsing"
    (testing "Going right"
      (is (= '([1 0] [2 0] [3 0]) (parse-segment "R3")))
      (is (= '([4 3] [5 3] [6 3]) (parse-segment "R3" [3 3]))))
    (testing "Going left"
      (is (= '([-1 0] [-2 0] [-3 0]) (parse-segment "L3")))
      (is (= '([2 3] [1 3] [0 3]) (parse-segment "L3" [3 3]))))
    (testing "Going up"
      (is (= '([0 1] [0 2] [0 3]) (parse-segment "U3")))
      (is (= '([3 4] [3 5] [3 6]) (parse-segment "U3" [3 3]))))
    (testing "Going down"
      (is (= '([0 -1] [0 -2] [0 -3]) (parse-segment "D3")))
      (is (= '([3 2] [3 1] [3 0]) (parse-segment "D3" [3 3])))))
  (testing "Circuit Building"
    (is (= #{[1 0] [2 0] [3 0] [4 0] [5 0]
             [6 0] [7 0] [8 0] [8 1] [8 2] [8 3]
             [8 4] [8 5] [7 5] [6 5] [5 5] [4 5]
             [3 5] [3 4] [3 3] [3 2]} (build-circuit ["R8" "U5" "L5" "D3"]))))

  (testing "Crossed Wires"
    (is (= 6 (part1 '(["R8" "U5" "L5" "D3"]
                      ["U7" "R6" "D4" "L4"]))))
    (is (= 159 (part1 '(["R75" "D30" "R83" "U83" "L12" "D49" "R71" "U7" "L72"]
                        ["U62" "R66" "U55" "R34" "D71" "R55" "D58" "R83"]))))
    (is (= 135 (part1 '(["R98" "U47" "R26" "D63" "R33" "U87" "L62" "D20" "R33" "U53" "R51"]
                        ["U98" "R91" "D20" "R16" "D67" "R40" "U7" "R15" "U6" "R7"]))))))
