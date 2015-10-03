package com.sky.exercise;

public class FatherClass
{
	public int a = 0;
	private String string = "Hello";

	static
	{
		System.out.println("FatherClass static code !");
	}

	public FatherClass()
	{
		System.out.println("FatherClass constructor code !");
	}

	public void method1()
	{
		System.out.println("FatherClass public method1");
	}

	public void fatherMethod()
	{
		System.out.println("FatherClass public fatherMethod");
	}
}
