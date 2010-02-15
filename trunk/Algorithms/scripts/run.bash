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
JVM_ARGS="-classpath ../bin -Xmx512m"

if [ $MODE = "timing" ]; then

	# Get args from input file
	ARGS=( )
	let count=0
	while read line
	do
		ARGS[$count]=$line
		((count++))
	done < 'Inputs/Input'$INPUT'.txt'

	java $JVM_ARGS Benchmark $ALG  ${ARGS[0]} ${ARGS[1]}
else
	# Get args from input file
	ARGS=( )
	let count=0
	while read line
	do
		ARGS[$count]=$line
		((count++))
	done < 'Inputs/benchmark'$INPUT'.txt'
	echo "java $JVM_ARGS Benchmark $ALG ABCD"
	java $JVM_ARGS Benchmark $ALG ABCD 
fi

