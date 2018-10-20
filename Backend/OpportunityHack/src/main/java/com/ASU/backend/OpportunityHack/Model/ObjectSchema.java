package com.ASU.backend.OpportunityHack.Model;

import javax.persistence.Entity;

@Entity
public class ObjectSchema {

    String tableName;

    ObjectParameters objectParameters;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ObjectParameters getObjectParameters() {
        return objectParameters;
    }

    public void setObjectParameters(ObjectParameters objectParameters) {
        this.objectParameters = objectParameters;
    }
}
