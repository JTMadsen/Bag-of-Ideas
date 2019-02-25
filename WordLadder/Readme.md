The aim of this task is to crate a chain from a given dictionary of words from one input word to another when the input word and output word are given
There is an optional input of "steps" which require the chain to be as long as the input of "steps"
When the optional steps field is not completed, the shortest path will be returned.

This uses a BFS for the shortest path, and a DFS for the path given steps.

To ensure speed of this process with large dictionary inputs, an adjacency list is created and nodes used (Thanks @Ruairi) minimizing the amount of time spent iteratiing through each chain.
