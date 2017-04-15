package com.sky.designpattern.decorator;

public class BlackBorderDecorator extends Decorator {

    public BlackBorderDecorator(Component view) {
        super(view);
    }

    @Override
    public void display() {
        System.out.println("Show Black border");
        super.display();
    }
}
