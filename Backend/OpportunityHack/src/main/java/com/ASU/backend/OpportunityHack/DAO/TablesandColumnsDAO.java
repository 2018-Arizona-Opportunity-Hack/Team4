package com.ASU.backend.OpportunityHack.DAO;

import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class TablesandColumnsDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getTableNames(){
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        List<String> result = entityManager.createNativeQuery(query).getResultList();
        return result;
    }

    public List<ObjectParameters> getTableColumns(String tableName){
        String query = "select column_name,data_type, is_nullable \n" +
                "from information_schema.columns \n" +
                "where table_name = ?";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1, tableName);
        List<Object[]> result = q.getResultList();
        List<ObjectParameters> result1 = new ArrayList<>();
        for(int i=0; i<result.size(); i++){
            ObjectParameters objectParameters = new ObjectParameters();
            objectParameters.setName((String)result.get(i)[0]);
            objectParameters.setDataType(modifyDataTypeName((String)result.get(i)[1]));
            objectParameters.setMandatory(changeBoolean((String)result.get(i)[2]));
            result1.add(objectParameters);
        }
        return result1;
    }

    public String modifyDataTypeName(String value){
        if(value.contains("char"))
            return "str";
        if(value.contains("int"))
            return "int";
        if(value.contains("time"))
            return "date";
        return "";
    }

    public Boolean changeBoolean(String value){
        return value.toLowerCase() =="yes"? true : false;
    }
}
