package org.example;

import java.util.Arrays;
import java.util.Collection;

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return this.getAge() - otherPerson.getAge();
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}


public class BoundedTypes {
    public static <T extends Comparable<T>> T getMinimum(T obj1, T obj2) {
        return obj1.compareTo(obj2) < 0 ? obj1 : obj2;
    }

    public static <T extends Comparable<T>> Integer countGreaterItems(T[] items, T item) {
        return (int) Arrays.stream(items).filter(element -> element.compareTo(item) > 0).count();
    }
    public static void main(String[] args) {
        Person p = new Person("person1", 70);
        Person p2 = new Person("person2", 40);

        System.out.println(countGreaterItems(new Integer[]{1,2,3,4,5},3));
    }
}
