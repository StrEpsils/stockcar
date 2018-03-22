package com.cherkasov.stockcar.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class Assamble implements Serializable {
    private static final long serialVersionUID = -6533931373211830537L;

    @JsonIgnore
    private int id;
    private Component component;
    @JsonIgnore
    private int id_assamble;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getId_assamble() {
        return id_assamble;
    }

    public void setId_assamble(int id_assamble) {
        this.id_assamble = id_assamble;
    }
}
