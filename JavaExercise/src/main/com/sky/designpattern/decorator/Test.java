package com.sky.designpattern.decorator;

public class Test {

    public static void main(String[] args) {
        Component component = new TextView();
        Component scrollBarTextView = new ScrollBarDecorator(component);
        Component scrollBarBlackBoardTextView = new BlackBorderDecorator(scrollBarTextView);

        scrollBarBlackBoardTextView.display();
    }
}
