(ns advent-2019.day06
  (:gen-class)
  (:require [clojure.string :as s]
            [advent-2019.core :refer [get-input]]))

(defn split-to-keywords [^String x]
  (->> x (#(s/split % #"\)")) (map keyword)))

(defn get-pairs [raw-data]
  (->> raw-data (map split-to-keywords)))

(defn into-map [acc [com body]]
  (update acc com #(conj % body)))

(defn build-orbit-map
  "Given a seq of keyword pairs seqs,
  Builds a map whose values are a set of bodies orbiting each key"
  [orbit-pairs]
  (->> (reduce into-map {} orbit-pairs)
       (reduce-kv #(assoc %1 %2 (into #{} %3)) {})))

(defn get-bodies
  "For a given center of mass, see if it has any bodies orbiting it
  If yes, return the bodies. If not, return the center of mass (itself)"
  [orbit-map com] (get orbit-map com com))

(defn build-orbit-tree
  "Build a tree of the orbit map beginning with cneter of mass com"
  [com orbit-map]
  (let [bodies (get-bodies orbit-map com)]
    (if (set? bodies)
      (conj (map #(build-orbit-tree % orbit-map) bodies) com)
      (list bodies))))

(defn tree-depths
  "For the given orbit tree, calculate the depth at each node"
  [orbit-tree]
  (let [walk (fn walk [depth node]
               (lazy-seq
                (cons [node depth]
                      (when (seq? node)
                        (mapcat (partial walk (inc depth)) (identity node))))))]
    (walk 0 orbit-tree)))

(def dec-val (comp dec (fn [[_ v]] v)))

(defn count-orbits
  "Given an orbit map, count the number of direct and indirect orbits"
  [orbit-tree]
  (->> orbit-tree
       tree-depths
       (filter (comp keyword? first))
       (map dec-val)
       (reduce +)))

(defn part1
  "What is the total number of direct and indirect orbits in your map data?"
  [input]
  (->> input
       get-pairs
       build-orbit-map
       (build-orbit-tree :COM)
       count-orbits))

(defn -main []
  (let [input (->> "day06.txt" get-input)]
    (time (println "Day 06, Part 1:" (part1 input)))
    #_(time (println "Day 06, Part 2:" (part2 input)))))
