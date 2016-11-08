package com.sky.designpattern.decorator;

public class Button implements Component {
    @Override
    public void display() {
        System.out.println("Show Button");
    }
}
