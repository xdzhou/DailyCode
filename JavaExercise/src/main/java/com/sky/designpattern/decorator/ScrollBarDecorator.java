package com.sky.designpattern.decorator;

public class ScrollBarDecorator extends Decorator {

    public ScrollBarDecorator(Component view) {
        super(view);
    }

    @Override
    public void display() {
        System.out.println("Show scroll bar");
        super.display();
    }
}
