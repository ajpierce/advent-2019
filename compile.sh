#!/bin/bash

DAY="day$1"

lein do clean, with-profile ${DAY} uberjar
native-image --report-unsupported-elements-at-runtime --initialize-at-build-time --allow-incomplete-classpath -jar ./target/advent2019-${DAY}.jar -H:Name=./target/${DAY}
