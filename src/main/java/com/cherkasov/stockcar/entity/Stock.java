package com.cherkasov.stockcar.entity;

import java.io.Serializable;

public class Stock implements Serializable {
    private static final long serialVersionUID = 4952103231982812796L;

    private int id_stock;
    private String name;

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
