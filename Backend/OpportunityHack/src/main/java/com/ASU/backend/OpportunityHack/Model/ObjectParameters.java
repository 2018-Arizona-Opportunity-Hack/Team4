package com.ASU.backend.OpportunityHack.Model;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class ObjectParameters {

    String name;

    Integer dataType;

    Boolean mandatory;

    Boolean unique;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }
}
