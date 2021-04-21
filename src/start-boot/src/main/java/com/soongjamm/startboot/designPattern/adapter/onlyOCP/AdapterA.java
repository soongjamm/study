package com.soongjamm.startboot.designPattern.adapter.onlyOCP;

public class AdapterA extends AClass{

    BClass bClass;

    public AdapterA(BClass bClass) {
        this.bClass = bClass;
    }

    @Override
    public void runA() {
        bClass.runB();
    }
}
