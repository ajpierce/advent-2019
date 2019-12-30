(ns advent-2019.day06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-2019.day06 :refer [build-orbit-map
                                       build-orbit-tree
                                       count-orbits
                                       get-bodies
                                       get-pairs
                                       split-to-keywords]]))

(def raw-orbit-data ["COM)B" "B)C" "C)D"
                     "D)E" "E)F" "B)G" "G)H"
                     "D)I" "E)J" "J)K" "K)L"])
(def orbit-map (->> raw-orbit-data get-pairs build-orbit-map))
(def orbit-tree (build-orbit-tree :COM orbit-map))

(deftest day06
  (testing "split-to-keywords"
    (is (= '(:B :C) (split-to-keywords "B)C"))))

  (testing "Parsing Orbit Data"
    (is (= {:COM #{:B} :B #{:G :C} :C #{:D} :D #{:I :E} :E #{:J :F} :G #{:H} :J #{:K} :K #{:L}}
           (->> raw-orbit-data get-pairs build-orbit-map))))

  (testing "Getting bodies"
    (is (= '(:G :C) (get-bodies orbit-map :B)))
    (is (= :L (get-bodies orbit-map :L))))

  (testing "Building orbit tree"
    (is (= '(:COM (:B (:G (:H)) (:C (:D (:I) (:E (:J (:K (:L))) (:F))))))
           (build-orbit-tree :COM orbit-map)))
    (is (= '(:E (:J (:K (:L))) (:F)) (build-orbit-tree :E orbit-map)))
    nil)

  (testing "Counting orbits"
    (is (= 42 (count-orbits orbit-tree)))
    nil))
