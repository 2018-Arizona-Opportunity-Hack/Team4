package com.ASU.backend.OpportunityHack.Model;

import org.springframework.stereotype.Component;

@Component
public class Query {
    private String query;
    private String format;

    public Query() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Query(String query, String format) {
        this.query = query;
        this.format = format;
    }
}
