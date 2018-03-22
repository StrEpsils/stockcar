package com.cherkasov.stockcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Feature implements Serializable {
    private static final long serialVersionUID = 1553586175300754246L;

    @JsonIgnore
    private int id_feature;
    private String name;

    public int getId_feature() {
        return id_feature;
    }

    public void setId_feature(int id_feature) {
        this.id_feature = id_feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
