package org.example.wildcards;

import java.util.ArrayList;
import java.util.List;

public class BoundedWildcards {
    public static <T extends Number> void addToList(List<T> list,T item){
        list.add(item);
    }

    public static void addToListUpperBounded(List<? extends Shape> list){
        // We cannot add when we are using UpperBound wild cards
        //list.add(item);
    }
    public static void showTheListUpperBounded(List<? extends Shape> list){
        // We can read elements when we are using UpperBound wild cards
        for(Shape s:list){
            s.draw();
        }
    }

    public static void addToListLowerBounded(List<? super Rectangle> list){
        // We can add when we are using LowerBound wild cards
        list.add(new Rectangle());
    }

    public static void showTheListLowerBounded(List<? super Rectangle> list){
        // We cannot show when we are using LowerBound wild cards
        for(Object shape:list){
            if(shape instanceof Shape){
                ((Shape) shape).draw();
            }
        }
    }
    public static void main(String[] args) {
        List<Integer> l1= new ArrayList<>();
        addToList(l1,1);

        ///
        List<Rectangle> rectangleList = new ArrayList<>();
        addToListUpperBounded(rectangleList);
        showTheListUpperBounded(rectangleList);

        //

        List<Shape> shapeList = new ArrayList<>();
        addToListLowerBounded(shapeList);
        showTheListLowerBounded(shapeList);
    }
}
