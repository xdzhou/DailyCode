package com.sky.designpattern.singleton;

public class Singleton
{
	/* ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */
	private static Singleton instance = null;

	/* ˽�й��췽������ֹ��ʵ���� */
	private Singleton()
	{
	}

	// ////////////////////////////////////////
	/* ��̬���̷���������ʵ�� */
	public static Singleton getInstance1()
	{
		if (instance == null)
		{
			createInstance();
			;
		}
		return instance;
	}

	private static synchronized void createInstance()
	{
		if (instance == null)
			instance = new Singleton();
	}

	// ///////////////////////////////////////////
	private static class singletonFactory
	{
		private static Singleton inst = new Singleton();
	}

	public static Singleton getInstance2()
	{
		return singletonFactory.inst;
	}
}
