package com.cherkasov.stockcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class StockComponent implements Serializable {
    private static final long serialVersionUID = -3353242744819188878L;

    private Component component;
    @JsonIgnore
    private Stock stock;
    private int count;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
