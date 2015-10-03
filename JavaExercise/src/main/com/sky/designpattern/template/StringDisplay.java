package com.sky.designpattern.template;

public class StringDisplay extends AbstractDisplay {
	private String string;  //应输出的字符串  
    private int width;      //以byte为单位所求出的字符串的"长度"  
      
    public StringDisplay(String string) {  
        this.string =string;  
        width = string.length();  
    }  
  
    public void open() {  //打印头装饰字符串  
        printLine();  
    }  
    public void print() { //打印内容  
        System.out.println("|"+string+"|");  
    }  
    public void close() { //打印尾装饰字符串  
        printLine();  
    }  
  
    public void printLine() {  
        System.out.print("+");  //输出"+"号表示边框位置  
        for(int i=0; i < width; ++i) {  
            System.out.print("-");  //当作线段  
        }  
        System.out.println("+");  //输出"+"号表示边框位置  
    }  

}
