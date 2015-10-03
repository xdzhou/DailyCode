package com.loic.algo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class MaxPQTest {
	private int N =100;
	private PriorityQueue<Double> pq_java;
	private MaxPQ<Double> pq_my;
	
	@BeforeClass
	public void beforeClass() {	
		Comparator<Double> cmp = new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return (int) (o2-o1);
			}
		};
		pq_java = new PriorityQueue<Double>(N,cmp);
		pq_my = new MaxPQ<Double>(N);
		for(int i=0; i<N; i++){
			double d = Math.random()*100;
			pq_java.add(d);
			pq_my.insert(d);
		}
	}

	@AfterClass
	public void afterClass() {
	}
	
	@Test
	public void MaxPQtest() {
		for(int i=0; i<N; i++){
			double d1 = pq_java.poll();
			double d2 = pq_my.delMax();
			System.out.println("d1="+d1+" d2="+d2);
			Assert.assertTrue(d1==d2);
		}
	}
}
