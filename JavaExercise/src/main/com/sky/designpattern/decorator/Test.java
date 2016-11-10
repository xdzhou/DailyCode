package com.sky.designpattern.decorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        Component component = new TextView();
        Component scrollBarTextView = new ScrollBarDecorator(component);
        final Component scrollBarBlackBoardTextView = new BlackBorderDecorator(scrollBarTextView);

        Component loggedComponent = new LoggedDecorator(scrollBarBlackBoardTextView);
        loggedComponent.display();

        loggedComponent = (Component) Proxy.newProxyInstance(scrollBarBlackBoardTextView.getClass().getClassLoader(), new Class[]{Component.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Log : proxy : before do "+method);
                Object obj = method.invoke(scrollBarBlackBoardTextView, args);
                System.out.println("Log : proxy : after do "+method);
                return obj;
            }
        });

        loggedComponent.display();
    }
}
