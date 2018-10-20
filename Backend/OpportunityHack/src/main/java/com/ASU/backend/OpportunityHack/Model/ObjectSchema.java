package com.ASU.backend.OpportunityHack.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

//@Entity
@Component
public class ObjectSchema {

    String tableName;

    List<ObjectParameters> objectParameters;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ObjectParameters> getObjectParameters() {
        return objectParameters;
    }

    public void setObjectParameters(List<ObjectParameters> objectParameters) {
        this.objectParameters = objectParameters;
    }
}
