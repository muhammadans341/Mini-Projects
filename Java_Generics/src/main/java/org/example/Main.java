package org.example;

import java.util.Arrays;

class Store<T>{
    private T item;
    public Store(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        this.item = item;
    }
    @Override
    public String toString() {
        return "Store{" +
                "item=" + item +
                '}';
    }
}
class HashTable<K,V>{
    private K key;
    private V value;

    public HashTable(K key,V value) {
        this.value=value;
        this.key = key;
    }
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

class GenericMethods{
    public <T> T genericMethod(T item){
        System.out.println("value is  ,"+ item);
        return item;
    }

    public <T extends Number> double add(T num1, T num2){
        return num1.doubleValue()+num2.doubleValue();
    }
    public <T> void showItems(T[] items){
        Arrays.stream(items).forEach(System.out::println);
    }
}
public class Main {
    public static void main(String[] args) {
        Store<Integer> store = new Store<>(10);
        System.out.println(store.getItem());

        Store<String> store2 = new Store<>("10");
        System.out.println(store2);

        System.out.println("-----Key value types-----");

        HashTable<Integer,Integer> hashTable = new HashTable<>(10,10);
        System.out.println(hashTable);

        System.out.println("-----Generic Methods-----");
        GenericMethods genericMethods = new GenericMethods();
        Integer t =  genericMethods.genericMethod(10);
        System.out.println(t);

        String s= genericMethods.genericMethod("test");
        System.out.println(s);
        System.out.println("----printing numbers-----");
        Integer[] nums= {1,2,3,4,5};
        genericMethods.showItems(nums);
        System.out.println("----printing names-----");
        String[] names = {"a","b","c","d"};
        genericMethods.showItems(names);


    }
}