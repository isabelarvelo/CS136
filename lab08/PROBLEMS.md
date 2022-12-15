# Lexicon Thought Questions

 1. For each node in the trie, you need to store pointers to its children nodes.
 What data structure did you use to store the pointers to children nodes?
 Justify the choice you made.

   * Data structure choice:
   Ordered Vector

   * Justification:
   We chose an ordered vector because we want to create a dynamically sized array
   that is always sorted so that when new elements are added, they are inserted into the correct position. Since the lexicon node implements Comparable<LexiconNode> we already provide a natural ordering for the vector. Add and remove are both O(n) and locate and contains are O(log n). While this data structure does not support the fastest operations, there are at most 26 children so all of these operations are still O(1). We are willing to sacrifice some efficiency in order for data structure that provides the functionality we need with minimal overhead.

2. Suppose we use an `OrderedVector` instead of a trie for storing our lexicon. Discuss how the process of searching for suggested spelling corrections would differ from our trie-based implementation. Which is more efficient? Why?
   * Searching in a trie: O(log n)
   * Searching in an `OrderedVector`:O (n^2)
   * Which is more efficient? Searching an ordered vector where each entry holds a word take O(n^2) time because it take O(n) time to look through each of the entries in the vector and it also take O(n) time to look through each character in each letter when comparing it to the desired word. A trie takes O(log n) time to search through because we are able to break down the word and can compare it to less words that are irrelevant and completely different than the given word. So, overall a trie is much more efficient than an ordered vector would be for storing LexiconNodes.
   * $ Let’s think about the performance of a lookup in both data structures. In a Trie, we must walk through a tree, traversing one node for each letter in the input word, so the cost is O(word.length()); even though we have to search all children for a given character, there are O(26) = O(1) children. In an OrderedVector, we can use binary search to find a word in O(log_2 n) time, where n is the total number of words in the lexicon. But suggesting a spelling correction isn’t the same as finding a word. To find all of the words in an OrderedVector that differ by X characters from the input word, you can’t just search a single region of the OrderedVector. You would have to search through all words in the Lexicon (you don’t know if the first letter differs, the second letter, etc.). In a Trie, all words that share the same prefix will share the same nodes for that prefix. So words that are similarly spelled will share a common path. 
