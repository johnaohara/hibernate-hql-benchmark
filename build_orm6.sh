#!/usr/bin/env bash

mvn -P orm6 clean package && java -jar target/orm6-hql-benchmark.jar -wi 10 -i 20 -f 1