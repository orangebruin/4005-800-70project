#!/bin/bash

# Arguments
ALG=$1
INPUT=$2
MODE=$3

# Set the path
LCS_PATH=build/Debug/

# Set LCS Name
LCS=4005-800-70project

#Set input directory location
INPUT_DIR=Inputs/

# Get args from input file
ARGS=( )
let count=0
while read line
do
	ARGS[$count]=$line
	((count++))
done < 'Inputs/Input'$INPUT'.txt'

$LCS_PATH$LCS $ALG  ${ARGS[0]} ${ARGS[1]}

