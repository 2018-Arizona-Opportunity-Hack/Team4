package com.ASU.backend.OpportunityHack.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectParameters {

    String name;

    String dataType;

    Boolean isMandatory = false;

    Boolean isUnique = false;

    Boolean isObject = false;

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

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean unique) {
        isUnique = unique;
    }

    public Boolean getIsObject() {
        return isObject;
    }

    public void setIsObject(Boolean object) {
        isObject = object;
    }
}
