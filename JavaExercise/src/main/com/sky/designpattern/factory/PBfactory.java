package com.sky.designpattern.factory;

public class PBfactory implements Factory {

	@Override
	public Produit createProduit() {
		return new ProduitB();
	}

}
