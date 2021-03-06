package com.ppm.designpattern.chain;


import com.ppm.designpattern.chain.complex.AbstractRequest;
import com.ppm.designpattern.chain.complex.Handler1;
import com.ppm.designpattern.chain.complex.Handler2;
import com.ppm.designpattern.chain.complex.Handler3;
import com.ppm.designpattern.chain.complex.Request1;
import com.ppm.designpattern.chain.simple.ConcreteHandler;
import com.ppm.designpattern.chain.simple.ConcreteHandler1;


public class Client {

    public static void main(String[] args) {
        ConcreteHandler concreteHandler = new ConcreteHandler();
        ConcreteHandler1 concreteHandler1 = new ConcreteHandler1();

        concreteHandler.successor = concreteHandler1;
        concreteHandler1.successor = concreteHandler;

        concreteHandler.handleRequest("ConcreteHandler1");


        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        Handler3 handler3 = new Handler3();

        handler1.nextHandler = handler2;
        handler2.nextHandler = handler3;

        AbstractRequest abstractRequest1 = new Request1("request1");
        AbstractRequest abstractRequest2 = new Request1("request2");
        AbstractRequest abstractRequest3 = new Request1("request3");

        handler1.handleRequest(abstractRequest1);
        handler1.handleRequest(abstractRequest2);
        handler1.handleRequest(abstractRequest3);
    }
}
