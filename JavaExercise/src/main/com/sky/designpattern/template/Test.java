package com.sky.designpattern.template;

public class Test {

    public static void main(String[] args) {
        CharDisplay cd = new CharDisplay('A');
        cd.display();

        StringDisplay sd = new StringDisplay("Bonjour");
        sd.display();
    }

}
