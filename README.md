# advent-2019 ![](https://github.com/ajpierce/advent-2019/workflows/Clojure%20CI/badge.svg)

Once a year, we dust off our Clojure skills for a week or so before real life gets in the way :)

## Setup
1. Make sure you have [Java](https://adoptopenjdk.net/index.html?variant=openjdk13&jvmVariant=hotspot) installed.
1. Make sure you have [Clojure](https://clojure.org/guides/getting_started) installed.
1. Make sure you have [Leiningen](https://leiningen.org/) installed.

## Project Structure

+ The `resources/` folder contains puzzle input. Each day has its own input file.
+ The `test/` folder contains unit tests.
  - There is a file with unit tests for each day's puzzles.
  - Each day has two puzzles.
  - Each puzzle has multiple sample inputs. The sampe inputs and their answers are the unit tests.
+ Puzzle solutions and supporting functions live in the `src/` folder.
  - Common functions that may be used in multiple solutions, such as parsing input, live in `core.clj`
  - Puzzle solutions for each day live in an associated file (`day01.clj`, `day02.clj`, etc.).

## Running
Use the `lein run` command to run the solution for a given day:

```
$ lein trampoline run -m advent-2019.day01
```

The trampoline makes it run faster. From the [lein docs](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md#running-code)

> For long-running lein run processes, you may wish to save memory with the higher-order trampoline task, which allows the Leiningen JVM process to exit before launching your project's JVM.

On my computer, day01 takes about 1.5s to run:

```
$ time lein trampoline run -m advent-2019.day01
Day 01, Part 1: 3337766
Day 01, Part 2: 5003788

real    0m1.571s
...
```

This is pretty slow for what this program does.  But the above command will compile our code before running it.  If we compile our code ahead of time, we don't have to sit through the compilation!

## Running Faster
Compiling our solution to a jar means that the time it takes to run no longer includes the time to compile the code:

```
$ lein do clean, with-profile day01 uberjar

Compiling advent-2019.core
Compiling advent-2019.day01
Created /[...]/advent-2019/target/advent2019-0.1.0-SNAPSHOT.jar
Created /[...]/advent-2019/target/advent2019-day01.jar
```

```
$ time java -jar ./target/advent2019-day01.jar
Day 01, Part 1: 3337766
Day 01, Part 2: 5003788

real    0m0.519s
...
```

You can see we've shaved about 1 second off, reducing our execution time by about ~67%.

But we can do better. We need _more speed._

## Running SO FAST

Stay tuned for some GraalVM magic.
