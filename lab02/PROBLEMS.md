I am the sole author of the work in this repository.

# Lab 2 - Random Writing : Thought Questions

_Self Check_ Problem 3.2: What is the difference between the `add(v)` and `add(i,v)` methods of Vector?
  * The `add(v)` method potentially extends the vector by adding a new element v to the end. The `add(i,v)`
    method inserts a new value in the vector at the specified index i and moves the remaining elements
    in the vector size()-1 to the right.
  * $ Exactly!

_Self Check_ Problem 3.3: What is the difference between the `add(i,v)` method and the `set(i,v)` method?
  * As mentioned above, the `add(i,v)` method adds a new value inserts a new value in the vector at the specified index i and moves the remaining elements in the vector size()-1 to the right. The `set(i,v)`
  method doesn't extend the vector, but instead replaces the element at the desired index with the value
  v and returns the old value.
  * $ Yes!

_Self Check_ Problem 3.4: What is the difference between the `remove(v)` method (where `v` is an `Object` value), and the `remove(i)` (where `i` is an `int`)?
  * The `remove(v)` method (where `v` is an `Object` value) removes the first instance of the indicated
  Object v and the size decreases by 1. The `remove(i)` (where `i` is an `int`) removes and returns the element at position v of the vector.
  * $ remove(v) also has to search for the element before it can remove it, while remove(i) can directly access and remove the element.


Problem 3.6: This question asks about *trade-offs*. Consider a fictional class called `BitVector` that has an interface similar to `Vector`, but the values stored within the `BitVector` are all known to be `boolean` (the primitive type). Why might we choose to write a special-purpose class like `BitVector` instead of using a `Vector<Boolean>`? A good answer will (very) briefly compare any advantages a designer might gain from specialization vs. the advantages of using Java Generics.
  * We might choose to write a special-purpose class like `BitVector` instead of `Vector<Boolean>` because
  the primitive type `boolean` take up less space than the using the generic `Vector<Boolean>`. Once
  the values are in the bag they are going to take up object size space which is an unnecessary because of energy if all of the values are known to be booleans. The primary advantages are that it will take up less memory space and require less computing time.
