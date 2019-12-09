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

## Running ON METAL

There's quite a bit of overhead involved with running the JVM to execute your Java bytecode. But don't worry! There's a lot of good work being done with the [GraalVM](https://www.graalvm.org/docs/why-graal/) to compile Java bytecode down to machine code.

We can run this on "the metal." We can go _fast._

First, follow [BrunoBonacci's excellent instructions](https://github.com/BrunoBonacci/graalvm-clojure/blob/master/doc/clojure-graalvm-native-binary.md#step1---download-and-install-graalvm) to get GraalVM installed and on your path to replace your default Java installation.

If GraalVM is installed correctly, you should see:

```
$ java -version
openjdk version "11.0.5" 2019-10-15
OpenJDK Runtime Environment (build 11.0.5+10-jvmci-19.3-b05-LTS)
OpenJDK 64-Bit GraalVM CE 19.3.0 (build 11.0.5+10-jvmci-19.3-b05-LTS, mixed mode, sharing)
```

Then, we'll rebuild our JAR using GraalVM, and compile the new JAR down to machine code:

```
$ lein do clean, with-profile day01 uberjar
$ native-image --report-unsupported-elements-at-runtime --initialize-at-build-time -jar ./target/advent2019-day01.jar -H:Name=./target/day01
```

**We're on the metal now.** Our executable is over twice as large as the original JAR:
```
$ du -sh ./target/*
4.9M ./target/advent2019-day01.jar
10M  ./target/day01
```

...but it contains all of the Java and Clojure we need to run our program (in addition to the program itself) without use of the JVM. Large binaries are the tradeoff we make for superior performance and memory utilization.

**It is time.** We measure the speed:

```
$ time ./target/day01
Day 01, Part 1: 3337766
Day 01, Part 2: 5003788

real    0m0.004s
...
```

Our program is now **two whole orders of magnitude** faster than running a JAR on the JVM.  When compared with running our program using `lein run`, it is **39,275% faster.**

![Gotta go Fast](https://i.kym-cdn.com/photos/images/original/000/506/223/2ab.gif)
_I cannot take [credit](https://knowyourmeme.com/memes/gotta-go-fast) for this image._

Instead of solving fun adventofcode puzzles, we spent the first third of Advent proving that there's no compelling reason to NOT use Clojure in production:

+ It is faster to develop software using Clojure
+ It is faster and more memory-efficient to run (compiled) Clojure than running JARs on the JVM
+ Clojure suffers from far less entropy and churn than the JavaScript ecosystem, which further reduces maintenance cost
- But, like our large binary sizes, there's a high upfront cost to learning Clojure

But, just like Santa and his elves, Clojure is _fun_ and _magical!_ I do hope you consider taking the plunge!
