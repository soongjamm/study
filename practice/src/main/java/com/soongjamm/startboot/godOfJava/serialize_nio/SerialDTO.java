package com.soongjamm.startboot.godOfJava.serialize_nio;

import java.io.Serializable;

public class SerialDTO implements Serializable {
    static final long serialVersionUiD = 1L;

    transient private String bookType = "IT";
    private String bookName;
    transient private int bookOrder;
    private boolean bestSeller;
    private long soldPerDay;

    public SerialDTO(String bookName, int bookOrder, boolean bestSeller, long soldPerDay) {
        this.bookName = bookName;
        this.bookOrder = bookOrder;
        this.bestSeller = bestSeller;
        this.soldPerDay = soldPerDay;
    }

    @Override
    public String toString() {
        return "SerialDTO{" +
                "bookType='" + bookType + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookOrder=" + bookOrder +
                ", bestSeller=" + bestSeller +
                ", soldPerDay=" + soldPerDay +
                '}';
    }
}
