Questions (from the lab handout):

1. Problem 6.4 (ignore the average case)
    * If the worst case, bubble case is O(n^2). As is, bubble sort also has a
    best case running time of O(n^2). However, it can be optimized by ending the
    method if there are no swaps made in any iteration. If the list is originally
    in order, the first iteration would not require any swaps and the method
    would end resulting in O(n) running time.

2. Problem 6.13 (for those sorts that are not stable, explain why)
    * For similar reasons to BubbleSort, insertion and mergesort are stable
    because no exchange occurs between equal values. Selection sort is
    stable because on the ith iteration, it finds the maximum value in the first n-i
    elements of the list and swaps it with the n-ith value. Since the method to
    find the position of the max value moves from left to right and the last i
    elements are guarenteed to be in sorted order the rightmost instance of
    equal values will be swapped to the end first so they will maintain their
    left-to-right order. Quicksort is not stable because the partition does not
    guarantee a specific order on either side of the pivot. The algorithm swaps
     elements that are not next to each other so there is no guarantee that equal
     elements will remain in the same order.

3. Problem 6.14
    * No, this does not support a correctly functioning quicksort because there
    is no guarantee that the elements left of the pivot after the swap are less
    than the pivot. For example, if you had the list 5 6 1 2 3. The left most
    element 5 would be swapped with the element at position 3 in this case 2.
    Then after the first partition, the list would be 2 6 1 5 3. At this point,
    6, which is greater than 5, has no way of getting to the right of 5 because
    we are working recursively and shrinking our lists.

4. Provide a brief, high-level description of the purpose of the code
   in each submitted Java file.
   * The Answering Questions class: A class that takes in user input in the
   form of phone book data and implements several different comparators to
   sort students in the phone book to answer questions about them. In order to
   run this code using the given phonebook.txt file as input, type the
   following into the terminal:
   $ java AnswerQuestions <phonebook.txt
   * The Comparing class: creates several comparators that compare different
   attributes of a student. We use the comparators created in this class to
   compare different values that make up a student to answer the question asked
    in the Answering Questions class.
   * The MyVector class: A subclass of Vector<E> that implements selection sort
    with a given comparator.Overall, this class allows us to sort our data we
    read in from the file and organize it based on out desired trait.
   * Student class: Creates a student object that contains information relevant
   to that student. This includes Name, address, associated phone numbers and
   mailbox number.


5. Finally, demonstrate that your program answers the Phonebook
   questions in step 5 by pasting in your program’s output. Please
   format this nicely so that it is easy for us to see that you
   correctly answered each question!
```
Q1: The first name to appear in a printed phone book sorted by first name is: Aalayah Rasheed

Q2: The student with the lowest SU Box number is Julia B Nawrocki(1000). The student with the highest SU Box number is Kristof Redei(5008)

Q3: The student with the most vowels in his full name is: Pierre-Alexandre C Meloty-Kapella

Q4: The unique address shared by the most students is 10-12 Meadow Street. Their names are:
David V Eisenson
Chris Ellis-Ferrara
Juan P Carro
Ezra A Burch
Philip F W Arnolds

Q5: The ten most common area codes for student home phone numbers in decreasing order are:
203 (86 occurrences)
413 (85 occurrences)
718 (79 occurrences)
914 (63 occurrences)
978 (58 occurrences)
781 (57 occurrences)
212 (51 occurrences)
860 (47 occurrences)
973 (47 occurrences)
617 (46 occurrences)
508 (45 occurrences)

```
