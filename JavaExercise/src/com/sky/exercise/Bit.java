package com.sky.exercise;

public class Bit {
	private int bits = 0;

	public static void main(String[] args) {
		Bit bit = new Bit();
		bit.showBit();
		bit.setBit(31, 1);
		bit.setBit(0, 1);
		bit.setBit(0, 0);
		bit.showBit();
	}
	
	public void setBit(int position, int bit){
		if(position>=0 && position<=31){
			int currentBit = ((bits>>>position)&1);
			//System.out.println("current bit = "+currentBit);
			if( bit !=  currentBit){
				bits = bits ^ (1<<position);
			}
		}
	}
	
	public void showBit(){
		for(int i=31; i>=0; i--){
			System.out.print(((bits>>>i)&1));
		}
		System.out.println();
	}
	

}
