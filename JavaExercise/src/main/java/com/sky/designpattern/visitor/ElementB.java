package com.sky.designpattern.visitor;

public class ElementB implements Element {

    @Override
    public void accept(Visitor v) {
        v.visit(this);

    }

}
