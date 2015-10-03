package com.sky.designpattern.factory;

public class PAfactory implements Factory
{

	@Override
	public Produit createProduit()
	{
		return new ProduitA();
	}

}
