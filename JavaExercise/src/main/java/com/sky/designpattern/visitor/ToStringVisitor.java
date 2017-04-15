package com.sky.designpattern.visitor;

//this visitor used to print a string
public class ToStringVisitor implements Visitor {

    @Override
    public void visit(ElementA ea) {
        System.out.println("Element A");

    }

    @Override
    public void visit(ElementB eb) {
        System.out.println("Element B");

    }

}
