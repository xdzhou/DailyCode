package com.sky.designpattern.singleton;

public class Singleton {
	/* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
	private static Singleton instance = null;
	
	/* 私有构造方法，防止被实例化 */
	private Singleton(){		
	}
	
	//////////////////////////////////////////
	 /* 静态工程方法，创建实例 */
	public static Singleton getInstance1(){
		if(instance == null){
			createInstance();;
		}
		return instance;
	}
	
	private static synchronized void createInstance(){
		if(instance == null) instance = new Singleton();
	}
	
	/////////////////////////////////////////////
	private static class singletonFactory{
		private static Singleton inst = new Singleton();
	}
	
	public static Singleton getInstance2(){
		return singletonFactory.inst;
	}
}
