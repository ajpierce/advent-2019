(defproject advent-2019 "0.1.0-SNAPSHOT"
  :description "Solutions to the 2019 Advent of Code"
  :url "https://github.com/ajpierce/advent-2019"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :profiles {:uberjar {:aot :all}
             :day01 {:main advent-2019.day01
                     :uberjar-name "advent2019-day01.jar"
                     :aot :all}
             :day02 {:main advent-2019.day02
                     :uberjar-name "advent2019-day02.jar"
                     :aot :all}
             :day03 {:main advent-2019.day03
                     :uberjar-name "advent2019-day03.jar"
                     :aot :all}}
  :repl-options {:init-ns advent-2019.core})
