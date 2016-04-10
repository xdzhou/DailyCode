package com.sky.designpattern.visitor;

//every visitor has a function special
public interface Visitor {

	public void visit(ElementA ea);

	public void visit(ElementB eb);

}
