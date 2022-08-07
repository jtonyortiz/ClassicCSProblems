/*
 * Author: James Ortiz
 * File: Fibonacci.java
 * Description: Different implmentation ofthe Fibonacci Sequence 
 * used as examples of optimizations.
 * 
 * Compile: javac Fibonacci.java
 *          java Fibonacci
 */ 



import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fibonacci {
    public static int fib1(int n){
        return fib1(n - 1) + fib1(n - 2);
    }

    //Naive version of Fibonacci:
    public static int fib2(int n) {
        if (n < 2) {
            return n;
        }else {
            return fib2(n - 1) + fib2(n - 2);
        }
    }

    // Map.of() was introduced in Java 9 but returns
    // an immutable Map
    // This creates a map with 0->0 and 1->1
    // which represent our base cases
    // HashMap version of fibonacci sequence:
    private static int fib3(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        //Base case for HashMap:
        memo.put(0,0);
        memo.put(1,1);

        //Use recursion to add new values for HashMap
        if (!memo.containsKey(n)) {
            // memoization step
            memo.put(n, fib3(n - 1) + fib3(n - 2));
        }
        return memo.get(n);
    }

    //Memoized version of Fibonacci done Iteratively
    //through the useage of a variable
    private static int fib4(int n) {
        int last = 0, next = 1; // fib(0), fib(1)
        for (int i = 0; i < n; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }


    //Version of Fibonacci using Java Streams:
    int last = 0;
    int next = 1;
    public IntStream stream() {
        //Lambda number stream useing Memoization
        return IntStream.generate(() -> {
            int oldLast = last;
            last = next;
            next = oldLast + next;
            return oldLast;
        });
    }
 



    public static void main(String[] args) {
       System.out.println(fib2(5));
       System.out.println(fib2(10));

       System.out.println("Fib V3 for 40 as n is: " + fib3(40));

       System.out.println("Fib V4 for 40 as n is: " + fib4(40));

       System.out.println("Fib using streams: ");
       Fibonacci fibStream = new Fibonacci(); //Creating an instance of the obj. for the stream:
       fibStream.stream().limit(41).forEachOrdered(System.out::println);

    }
}