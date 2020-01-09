(defproject advent-2019 "0.1.0-SNAPSHOT"
  :description "Solutions to the 2019 Advent of Code"
  :url "https://github.com/ajpierce/advent-2019"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "0.6.532"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [com.taoensso/tufte "2.1.0"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.0"]]
  :global-vars {*warn-on-reflection* true}
  :profiles {:uberjar {:aot :all}
             :day01 {:main advent-2019.day01
                     :uberjar-name "advent2019-day01.jar"
                     :native-image {:name     "day01"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day02 {:main advent-2019.day02
                     :uberjar-name "advent2019-day02.jar"
                     :native-image {:name     "day02"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day03 {:main advent-2019.day03
                     :uberjar-name "advent2019-day03.jar"
                     :native-image {:name     "day03"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day04 {:main advent-2019.day04
                     :uberjar-name "advent2019-day04.jar"
                     :native-image {:name     "day04"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day05 {:main advent-2019.day05
                     :uberjar-name "advent2019-day05.jar"
                     :native-image {:name     "day05"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day06 {:main advent-2019.day06
                     :uberjar-name "advent2019-day06.jar"
                     :native-image {:name     "day06"
                                    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                                    :opts     ["--report-unsupported-elements-at-runtime"
                                               "--initialize-at-build-time"
                                               "--allow-incomplete-classpath"
                                               "--no-server" ;;avoid spawning build server
                                               "-H:ConfigurationResourceRoots=resources"
                                               ~(str "-H:ResourceConfigurationFiles="
                                                     (System/getProperty "user.dir")
                                                     (java.io.File/separator)
                                                     "resource-config.json")]}
                     :aot :all}
             :day07 {:main advent-2019.day07
                     :uberjar-name "advent2019-day07.jar"
                     :aot :all}}
  :repl-options {:init-ns advent-2019.core})
