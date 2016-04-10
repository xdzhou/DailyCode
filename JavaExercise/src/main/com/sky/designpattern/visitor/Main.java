package com.sky.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Element> lists = new ArrayList<Element>(2);
		lists.add(new ElementA());
		lists.add(new ElementB());

		ToStringVisitor toStringVisitor = new ToStringVisitor();
		IntVisitor intVisitor = new IntVisitor();

		for (Element e : lists) {
			e.accept(toStringVisitor);
			e.accept(intVisitor);
		}
	}

}
