#!/usr/bin/env bash

mvn -P orm5 clean package && java -jar target/orm5-hql-benchmark.jar -wi 10 -i 20 -f 1