package com.ASU.backend.OpportunityHack.Model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonResp {
    private String headers;
    private List<List<String>> data;

    public JsonResp() {
    }

    public JsonResp(String headers, List<List<String>> data) {
        this.headers = headers;
        this.data = data;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
}
