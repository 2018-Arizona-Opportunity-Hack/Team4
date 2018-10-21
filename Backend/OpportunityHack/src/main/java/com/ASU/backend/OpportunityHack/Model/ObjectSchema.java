package com.ASU.backend.OpportunityHack.Model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Component
public class ObjectSchema {

    String tableName;

    List<ObjectParameters> objectParameters;

    private Boolean isEventTable;

    @JsonGetter("entityName")
    public String getTableName() {
        return tableName;
    }

    @JsonSetter("tableName")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @JsonGetter("attributes")
    public List<ObjectParameters> getObjectParameters() {
        return objectParameters;
    }

    @JsonSetter("objectParameters")
    public void setObjectParameters(List<ObjectParameters> objectParameters) {
        this.objectParameters = objectParameters;
    }

    public Boolean getIsEventTable() {
        return isEventTable;
    }

    public void setIsEventTable(Boolean eventTable) {
        isEventTable = eventTable;
    }
}
