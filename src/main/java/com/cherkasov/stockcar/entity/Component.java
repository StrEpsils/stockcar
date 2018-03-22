package com.cherkasov.stockcar.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

public class Component implements Serializable {
    private static final long serialVersionUID = 9109103818702222472L;

    private int id_component;
    private String name;
    @JsonIgnore
    private boolean ending;
    private List<Assamble> assamble;
    private int id_assamble;

    public int getId_component() {
        return id_component;
    }

    public void setId_component(int id_component) {
        this.id_component = id_component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnding() {
        return ending;
    }

    public void setEnding(boolean ending) {
        this.ending = ending;
    }

    public List<Assamble> getAssamble() {
        return assamble;
    }

    public void setAssamble(List<Assamble> assamble) {
        this.assamble = assamble;
    }

    public int getId_assamble() {
        return id_assamble;
    }

    public void setId_assamble(int id_assamble) {
        this.id_assamble = id_assamble;
    }
}
