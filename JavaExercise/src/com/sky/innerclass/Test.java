package com.sky.innerclass;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.sky.innerclass.Transaction.priceOrder;

public class Test {

	public static void main(String[] args) {
		//test 1
		Set<Transaction> set = new TreeSet<Transaction>(new priceOrder());
		for(int i=0; i< 10; i++) set.add(new Transaction());
		Iterator<Transaction> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		//test 2
		MultiParent parents = new MultiParent();
		System.out.println(parents.getElementA());
		System.out.println(parents.getElementA());
		System.out.println(parents.getElementA());
	}

}
