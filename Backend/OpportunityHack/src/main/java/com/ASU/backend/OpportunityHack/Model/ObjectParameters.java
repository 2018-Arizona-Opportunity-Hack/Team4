package com.ASU.backend.OpportunityHack.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectParameters {

    String name;

    String dataType;

    Boolean mandatory;

    Boolean unique;

    Boolean isObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
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

    public Boolean getIsObject() {
        return isObject;
    }

    public void setIsObject(Boolean object) {
        isObject = object;
    }
}
