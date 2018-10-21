package com.ASU.backend.OpportunityHack.Model;

import com.ASU.backend.OpportunityHack.Attribute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ObjectData {
    private String entityName;
    private ArrayList<Attribute> attributes;

    public ObjectData() {
    }

    public ObjectData(String entityName, ArrayList<Attribute> attributes) {
        this.entityName = entityName;
        this.attributes = attributes;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }
}
