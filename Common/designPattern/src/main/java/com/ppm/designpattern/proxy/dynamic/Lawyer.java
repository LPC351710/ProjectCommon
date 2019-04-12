package com.ppm.designpattern.proxy.dynamic;

public class Lawyer implements ILawsuit {

    private ILawsuit lawsuit;

    public Lawyer(ILawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    @Override
    public void submit() {
        lawsuit.submit();
    }

    @Override
    public void burden() {
        lawsuit.burden();
    }

    @Override
    public void defend() {
        lawsuit.defend();
    }

    @Override
    public void finish() {
        lawsuit.finish();
    }
}
