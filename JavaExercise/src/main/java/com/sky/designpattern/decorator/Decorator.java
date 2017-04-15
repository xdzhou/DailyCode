package com.sky.designpattern.decorator;

public class Decorator implements Component {
    private final Component mView;

    public Decorator(Component view) {
        mView = view;
    }

    @Override
    public void display() {
        mView.display();
    }
}
