package com.ppm.designpattern.chain.complex;

public abstract class AbstractHandler {

    public AbstractHandler nextHandler;

    public final void handleRequest(AbstractRequest request) {
        if (getHandleLevel() == request.getRequestLevel()) {
            handle(request);
        } else {
            if (nextHandler != null) {
                nextHandler.handle(request);
            } else {
                System.out.println("All of Handler can not handle the request");
            }
        }
    }

    public abstract int getHandleLevel();

    protected abstract void handle(AbstractRequest request);
}
