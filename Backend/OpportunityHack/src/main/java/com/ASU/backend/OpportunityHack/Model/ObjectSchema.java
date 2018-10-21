package com.ASU.backend.OpportunityHack.Model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Component
public class ObjectSchema {

    String entityName;

    List<ObjectParameters> attributes;

    private Boolean isEventTable;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<ObjectParameters> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ObjectParameters> attributes) {
        this.attributes = attributes;
    }

    public Boolean getIsEventTable() {
        return isEventTable;
    }

    public void setIsEventTable(Boolean eventTable) {
        isEventTable = eventTable;
    }
}
