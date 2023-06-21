package org.example;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        Function<Integer,Integer> incrementBy10 = number -> number +10;
        incrementBy10.apply(10);

        Function<Integer,Integer> multiplyBy20 = number -> number *20;
        multiplyBy20.apply(10);

        //Chaining Functions
        Function<Integer,Integer> incrementBy10AndMultiply20 = incrementBy10.andThen(multiplyBy20);
        System.out.println(incrementBy10AndMultiply20.apply(10));

        BiFunction<Integer,Integer,Integer> addTwoNumbers = (num1,num2) -> num1+num2;
        System.out.println(addTwoNumbers.apply(10,20));


    }
}
