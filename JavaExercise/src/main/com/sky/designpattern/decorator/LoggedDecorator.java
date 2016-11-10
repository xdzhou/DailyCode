package com.sky.designpattern.decorator;

public class LoggedDecorator extends Decorator {

    public LoggedDecorator(Component view) {
        super(view);
    }

    @Override
    public void display() {
        System.out.println("Log : before display...");
        super.display();
        System.out.println("Log : after display...");
    }

}
