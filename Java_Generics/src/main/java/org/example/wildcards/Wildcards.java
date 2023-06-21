package org.example.wildcards;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {

    public static void drawAllTest(List<Shape> list){
        list.forEach(Shape::draw);
    }

    //This is an unbounded wild card
    public static void drawAllTestUnbounded(List<?> list){
        list.forEach(System.out::println);
    }
    public static void drawAll(List<? extends Shape> list){
        list.forEach(Shape::draw);
    }

    public static <T extends Shape>void drawAllGeneric(List<T> list){
        list.forEach(Shape::draw);
    }
    public static void main(String[] args) {
        List<Rectangle> rectangleList = new ArrayList<>();
        rectangleList.add(new Rectangle());
        drawAll(rectangleList);
        drawAllGeneric(rectangleList);
    }
}
