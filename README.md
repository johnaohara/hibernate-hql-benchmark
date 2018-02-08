# HQL Parsing Microbenchmark

A JMH micro benchmark to compare HQL parsing performance of different versions of Hibernate ORM

## Build

    mvn clean install

Then you can build ORM5 with

    mvn -P orm5 clean package

or ORM6

    mvn -P orm6 clean package 

Please refer to each maven profile to specify the right version of hibernate ORM

## Run

    java -jar target/orm6-hql-benchmark.jar -wi 10 -i 20 -f 1

where
  - wi ; number of warmup iterations
  - i ; number if measurement iterations
  - f ; number of benchmark forks
  
Additionally, all JMH settings are accepted. See JMH documentation for further details  
    
## Build and run

The most convenient way is just to call the scripts

    ./build_orm5.sh
    ./build_orm6.sh    

