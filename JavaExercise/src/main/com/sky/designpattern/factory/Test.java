package com.sky.designpattern.factory;

public class Test
{

	public static void main(String[] args)
	{
		Factory f = new PAfactory();
		Produit p = f.createProduit();
		System.out.println(p.getClass().getName());
	}

}
