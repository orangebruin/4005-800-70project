#!/bin/bash
ALGO="1 2 3 4"
INPUT_SET="0 1 2 3 4"
BENCHMARK_SET="0 1 2 3"


#Run standard input set tests
for A in $ALGO; do 
	for B in $INPUT_SET; do
		echo "Timing Algorithm: $A InputSet: $B" 
		./run.bash timing $A $B
	done
done


#Run timing modes (eg. how much can run in 10 seconds)
for A in $ALGO; do
	for B in $BENCHMARK_SET; do
		echo "Benchmarking Algorithm: $A CharacterSet: $B"
		./run.bash benchmark $A $B
	done 
done

#Run special case input sets
echo "Timing Algorithm 4 on 40,000 character input sets"
./run.bash max_input 4 
