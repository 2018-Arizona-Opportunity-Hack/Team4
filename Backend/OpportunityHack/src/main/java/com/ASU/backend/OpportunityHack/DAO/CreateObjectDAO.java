package com.ASU.backend.OpportunityHack.DAO;


import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import com.ASU.backend.OpportunityHack.Model.ObjectSchema;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Repository
@Transactional
public class CreateObjectDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Map<String, Object> createObject(ObjectSchema objectSchema){
        StringBuilder query = new StringBuilder("CREATE TABLE "+objectSchema.getTableName()+" (");
        query.append("id SERIAL PRIMARY KEY,");
        for(ObjectParameters obj : objectSchema.getObjectParameters()){
            query.append(obj.getName());
            query.append(" ");
            query.append(obj.getDataType());
            if(obj.getUnique())
                query.append(" UNIQUE");
            if(obj.getMandatory())
                query.append(" NOT NULL");
            query.append(",");
        }
        query.setLength(query.length()-1);
        query.append(")");
        System.out.println(query);
        entityManager.createNativeQuery(query.toString()).executeUpdate();
//        if()
        return null;
    }

//    public String returnDatatype(int i){
//        if(i == 1) return "INTEGER";
//        else if(i == 2) return "VARCHAR(100)";
//        else return "timestamp";
//    }
}
