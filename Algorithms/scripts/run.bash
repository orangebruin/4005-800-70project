#!/bin/bash

# Arguments
ALG=$2
INPUT=$3
MODE=$1

# Set LCS Name
LCS=4005-800-70project

#Set input directory location
INPUT_DIR=inputs/

#Classpath
CLASSPATH=../bin/

#JVM Args
JVM_ARGS="-classpath ../bin -Xmx1024m"

if [ $MODE = "timing" ]; then
	# Get args from input file
	ARGS=( )
	let count=0
	while read line
	do
		ARGS[$count]=$line
		((count++))
	done < 'inputs/input'$INPUT'.txt'
	java $JVM_ARGS Benchmark $ALG  ${ARGS[0]} ${ARGS[1]}
elif [ $MODE = "benchmark" ]; then
	# Get args from input file
	ARGS=( )
	let count=0
	while read line
	do
		ARGS[$count]=$line
		((count++))
	done < 'inputs/benchmark'$INPUT'.txt'
	java $JVM_ARGS -Dcsv=true Benchmark $ALG ATCG 
else
      # Get args from input file
        ARGS=( )
        let count=0
        while read line
        do
                ARGS[$count]=$line
                ((count++))
        done < 'inputs/input_max.txt'
	java $JVM_ARGS -Dnruns=1 -Dverbose=true Benchmark $ALG ${ARGS[0]} ${ARGS[1]}
fi
