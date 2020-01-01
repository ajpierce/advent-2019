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

(defn get-parent [omap node]
  (->> omap (filter (fn [[_ v]] (contains? v node))) keys first))

(defn get-common-ancestor
  "Given two nodes and an orbial map, return the first common anestor
  and the distance to that ancestor from each node"
  [node1 node2 omap]
  (loop [n1 node1 n2 node2 s1 {node1 0} s2 {node2 0}]
    (cond (contains? s2 n1) [n1 (get s1 n1) (get s2 n1)]
          (contains? s1 n2) [n2 (get s1 n2) (get s2 n2)]
          (and (nil? n1) (nil? n2)) nil
          :else
          (let [next1 (get-parent omap n1)
                next2 (get-parent omap n2)]
            (recur next1 next2
                   (assoc s1 next1 (dec (count s1)))
                   (assoc s2 next2 (dec (count s2))))))))

(defn part2
  "What is the minimum number of orbital transfers required to move
  from the object YOU are orbiting to the object SAN is orbiting?"
  [input]
  (->> input
       get-pairs
       build-orbit-map
       (get-common-ancestor :YOU :SAN)
       rest
       (reduce +)))

(defn -main []
  (let [input (->> "day06.txt" get-input)]
    (time (println "Day 06, Part 1:" (part1 input)))
    (time (println "Day 06, Part 2:" (part2 input)))))
