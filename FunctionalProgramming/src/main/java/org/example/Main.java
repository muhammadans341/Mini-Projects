package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.Main.Gender.FEMALE;
import static org.example.Main.Gender.MALE;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Alex",MALE),
                new Person("Kira",FEMALE),
                new Person("Scott",MALE),
                new Person("Stiles",MALE),
                new Person("Lydia",FEMALE)
        );

        persons.stream().filter(person -> person.gender==FEMALE).forEach(System.out::println);

        List<Person> temp = Collections.emptyList() ;
        temp.addAll(persons);
    }

    public static class Person{
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    public enum Gender{
        MALE,FEMALE
    }
}