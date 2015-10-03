package com.sky.innerclass;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class Transaction {
	private float price;
	private Date date;
	private String who;
	
	public Transaction(){
		Random r = new Random();
		price = r.nextFloat();
		date = new Date(r.nextLong());
		if(r.nextBoolean()) who = "hello";
		else who = "bonjour";
	}
	
	@Override
	public String toString() {
		return "Transaction [price=" + price + ", date=" + date + ", who="
				+ who + "]";
	}



	public static class priceOrder implements Comparator<Transaction>{
		@Override
		public int compare(Transaction o1, Transaction o2) {
			if(o1.price < o2.price) return -1;
			else if (o1.price > o2.price) return 1;
			else return 0;
		}
	}
	
	public static class dateOrder implements Comparator<Transaction>{
		@Override
		public int compare(Transaction o1, Transaction o2) {
			return o1.date.compareTo(o2.date);
		}	
	}
	
	public static class whoOrder implements Comparator<Transaction>{
		@Override
		public int compare(Transaction o1, Transaction o2) {
			return o1.who.compareTo(o2.who);
		}	
	}

}
