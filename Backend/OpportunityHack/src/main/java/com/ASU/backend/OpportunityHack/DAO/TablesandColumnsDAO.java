package com.ASU.backend.OpportunityHack.DAO;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<String> getTableColumns(){

    }
}
