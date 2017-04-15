package com.sky.designpattern.decorator;

public class TextView implements Component {

    @Override
    public void display() {
        System.out.println("Show Text view");
    }
}
