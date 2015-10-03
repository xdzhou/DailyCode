package com.sky.designpattern.template;
/** 
 * 抽象类,充当模板角色 
 * @author administrator 
 * 模板是指在薄片塑料板上面写字后挖空,再使用毛笔或色笔涂满挖空部分,就能看到纯手工而以不失工整的字样,
 * 看到模板上的挖空形状,马上就知道最后会变出什么样子的字,不过实际上所显现出来的字样还是要依所使用的画笔种类而定.
 * 拿黑色签字笔当画笔,结果当然就是签字笔的字样;当用铅笔来画,得到的也只会是灰黑色的铅笔字;
 * 如果用五颜六色的彩色笔,自然能创出让人眼花的多色字.但是,无论使用哪种文具,制作出来的字样都还是脱不了模板上已经固定的形状。
 */ 
public abstract class AbstractDisplay {
	//由子类实现的抽象方法  
    public abstract void open();     
    public abstract void print();  
    public abstract void close();  
    //抽象类实现的方法,final可以保证在子类不会被修改  
    public final void display() {  
        open();  
        for(int i=0; i < 5; i++) {  
            print();    
        }  
        close(); 
    }  
}
