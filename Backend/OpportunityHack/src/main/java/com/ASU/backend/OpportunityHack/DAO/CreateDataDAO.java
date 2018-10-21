package com.ASU.backend.OpportunityHack.DAO;

import com.ASU.backend.OpportunityHack.Attribute;
import com.ASU.backend.OpportunityHack.Model.ObjectData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CreateDataDAO {
    @PersistenceContext
    EntityManager entityManager;

    public String saveObject(ObjectData od) {
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Attribute a : od.getAttributes()) {
            if (od.getAttributes().indexOf(a) != od.getAttributes().size() - 1) {
                names.append(a.getName() + ",");
                values.append("'" + a.getValue() + "',");
            } else {
                names.append(a.getName());
                values.append("'" + a.getValue() + "'");
            }
        }
        String sql = "INSERT INTO " + od.getEntityName() + "(" + names + ")" + " values (" + values + ") ";
        System.out.println(sql);
        entityManager.createNativeQuery(sql).executeUpdate();
        return "ok";
    }

}
