package com.ASU.backend.OpportunityHack.DAO;

import com.ASU.backend.OpportunityHack.Attribute;
import com.ASU.backend.OpportunityHack.Model.ObjectData;
import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
@Transactional
public class CreateDataDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    TablesandColumnsDAO tablesandColumnsDAO;

    public String saveObject(ObjectData od) {
        System.out.println(od.getEntityName());
        System.out.println(od.getAttributes());
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<ObjectParameters> objectParameters = tablesandColumnsDAO.getTableColumns(od.getEntityName());
        List<String> dateColumns = new ArrayList<>();
        String value = "";
        for (ObjectParameters o : objectParameters) {
            if (o.getDataType().toLowerCase() == "timestamp") {
                dateColumns.add(o.getName());
                System.out.println(o.getName());
            }
        }
        int count = 1;
        for (Attribute a : od.getAttributes()) {
            if (dateColumns.contains(a.getName())) {
                try {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                    LocalDateTime date = LocalDateTime.parse(a.getValue(), inputFormatter);
                    Timestamp d = Timestamp.valueOf(date);
                    value = d.toString();
                } catch (Exception e) {
                    value = null;
                    e.printStackTrace();
                }
            } else {
                value = a.getValue();
            }
            if (value == null) {
                count++;
                continue;
            }

            if (od.getAttributes().indexOf(a) <=od.getAttributes().size() - count) {
                names.append(a.getName() + ",");
                values.append("'" + value + "',");
            } else {
                names.append(a.getName());
                values.append("'" + value + "'");
            }
        }
        String sql = "INSERT INTO " + od.getEntityName() + "(" + names + ")" + " values (" + values + ") ";
        System.out.println(sql);
        entityManager.createNativeQuery(sql).executeUpdate();
        return "";
    }

}
