package com.sky.designpattern.visitor;

//this visitor used to print a int
public class IntVisitor implements Visitor {

    @Override
    public void visit(ElementA ea) {
        System.out.println("1");

    }

    @Override
    public void visit(ElementB eb) {
        System.out.println("2");

    }

}
