package com.ASU.backend.OpportunityHack.DAO;

import com.ASU.backend.OpportunityHack.Model.JsonResp;
import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Repository
public class TablesandColumnsDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getTableNames() {
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        List<String> result = entityManager.createNativeQuery(query).getResultList();
        return result;
    }

    public List<ObjectParameters> getTableColumns(String tableName) {
        String query = "select column_name,data_type, is_nullable \n" +
                "from information_schema.columns \n" +
                "where table_name = ?";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1, tableName.toLowerCase());
        List<Object[]> result = q.getResultList();
        List<ObjectParameters> result1 = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            ObjectParameters objectParameters = new ObjectParameters();
            objectParameters.setName((String) result.get(i)[0]);
            objectParameters.setDataType(modifyDataTypeName((String) result.get(i)[1]));
            objectParameters.setIsMandatory(changeBoolean((String) result.get(i)[2]));
            result1.add(objectParameters);
        }
        return result1;
    }

    public HashSet<String> getEventsList() {
        String query = "select * from events_tables";
        Query q = entityManager.createNativeQuery(query);
        List<String> result = q.getResultList();
        HashSet<String> hs = new HashSet<>(result);
        return hs;
    }

    public String modifyDataTypeName(String value) {
        if (value.contains("char"))
            return "VARCHAR(500)";
        if (value.contains("int"))
            return "INTEGER";
        if (value.contains("time"))
            return "timestamp";
        return "";
    }

    public Boolean changeBoolean(String value) {
        return value.toLowerCase() == "yes" ? true : false;
    }

    public String getTableDataToExport(String query, String format) {
        //String s = "SELECT * FROM " + tableName;

        Query q = entityManager.createNativeQuery(query);
        List<Object[]> listOfRows = q.getResultList();
        String op = "";
        List<String> stringList = Arrays.asList(query.toLowerCase().split(" "));
        String tableName = stringList.get(stringList.indexOf("from") + 1);
        if (format.equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            List<List<String>> al = new ArrayList<>();
            JsonResp jsonResp = new JsonResp();
            jsonResp.setHeaders(String.valueOf(stringList.get(1)));
            for (int i = 0; i < listOfRows.size(); i++) {
                List<String> sl = new ArrayList<>();
                for (int j = 0; j < listOfRows.get(i).length; j++) {
                    sl.add(String.valueOf(listOfRows.get(i)[j] == null ? '\0' : listOfRows.get(i)[j]));
                }
                al.add(sl);
            }
            jsonResp.setData(al);
            try {
                return mapper.writeValueAsString(jsonResp);
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception in json to String";
            }
        }
        if (format.equals("csv")) {
            List<ObjectParameters> objectParameters = getTableColumns(tableName);
            for (ObjectParameters tmp : objectParameters) {
                if (objectParameters.indexOf(tmp) != objectParameters.size() - 1) {
                    op = op + tmp.getName() + ",";
                } else {
                    op = op + tmp.getName();
                }
            }
            op += "\n";
            for (int i = 0; i < listOfRows.size(); i++) {
                for (int j = 0; j < listOfRows.get(i).length; j++) {
                    if (j != listOfRows.get(i).length - 1) {
                        op = op + String.valueOf(listOfRows.get(i)[j] == null ? '\0' : listOfRows.get(i)[j]) + ",";
                    } else {
                        op = op + String.valueOf(listOfRows.get(i)[j] == null ? '\0' : listOfRows.get(i)[j]);
                    }
                }
                op += "\n";
            }
        }
        return op;

    }

    public List<Object> getRelatedTables(String tableName){
        Query q = entityManager.createNativeQuery("SELECT object_table_name from events_objects_mapping where events_table_name = ?");
        q.setParameter(1, tableName);
        List<Object> result = q.getResultList();
        return result;
    }
}
