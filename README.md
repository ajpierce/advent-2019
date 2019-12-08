# advent-2019 ![](https://github.com/ajpierce/advent-2019/workflows/Clojure%20CI/badge.svg)

Once a year, we dust off our Clojure skills for a week or so before real life gets in the way :)

## Setup
1. Make sure you have [Java](https://adoptopenjdk.net/index.html?variant=openjdk13&jvmVariant=hotspot) installed.
1. Make sure you have [Clojure](https://clojure.org/guides/getting_started) installed.
1. Make sure you have [Leiningen](https://leiningen.org/) installed.

## Project Structure
Puzzle input lives in the `resources/` folder. Each day has its own input file.

Each question has multiple sample inputs with solutions. These examples are used as unit tests. Tests live in the `test/` folder.

Puzzle solutions and supporting functions live in the `src/` folder.

+ Common functions that may be used in multiple solutions, such as parsing input, live in `core.clj`
+ Puzzle solutions for each day live in an associated file (`day01.clj`, `day02.clj`, etc.).

## Running
To run the solution for a given day, use the `lein run` command like so, replacing `01` with the number of the day whose solution you are interested in:

```
lein trampoline run -m advent-2019.day01
```

The trampoline makes it run faster. From the [lein docs](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md#running-code)

> For long-running lein run processes, you may wish to save memory with the higher-order trampoline task, which allows the Leiningen JVM process to exit before launching your project's JVM.
