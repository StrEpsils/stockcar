package com.cherkasov.stockcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class FeatureValue implements Serializable {
    private static final long serialVersionUID = -973213950918363087L;

    @JsonIgnore
    private int id_feature_value;
    private String value;
    private Feature feature;
    @JsonIgnore
    private int id_component;

    public int getId_feature_value() {
        return id_feature_value;
    }

    public void setId_feature_value(int id_feature_value) {
        this.id_feature_value = id_feature_value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public int getId_component() {
        return id_component;
    }

    public void setId_component(int id_component) {
        this.id_component = id_component;
    }
}
