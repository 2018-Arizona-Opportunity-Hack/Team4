package com.ASU.backend.OpportunityHack.DAO;

import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class BulkInsertDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    TablesandColumnsDAO tablesandColumnsDAO;

    public Map<String, Object> insertCSV(String tableName, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        List<ObjectParameters> objectParameters = tablesandColumnsDAO.getTableColumns(tableName);
        List<Integer> intColumns = new ArrayList<>();
        for (ObjectParameters objectPrms : objectParameters) {
            if (objectPrms.getDataType().toLowerCase().contains("integer")) {
                intColumns.add(objectParameters.indexOf(objectPrms));
            }
        }
        StringBuilder query = new StringBuilder("insert into " + tableName + " (");
        List<String> list = Arrays.asList(line.split(","));
        for (String str : list) {
            String[] stringArray = str.split("\\(");
            query.append(stringArray[0]);
            query.append(",");
        }
        query.setLength(query.length() - 1);
        query.append(") ");
        query.append("values");
        query.append(" ");
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.isEmpty()) {
                List<String> row = Arrays.asList(line.split(","));
                query.append("(");
                for (String r : row) {
                    if (!intColumns.contains(row.indexOf(r) + 1)) {
                        query.append("'");
                    }
                    query.append(r);
                    if (!intColumns.contains(row.indexOf(r) + 1)) {
                        query.append("',");
                    } else {
                        query.append(",");
                    }

                }
                query.setLength(query.length() - 1);
                query.append(")");
                query.append(",");
            }
        }
        query.setLength(query.length() - 1);
        System.out.println(query);
        entityManager.createNativeQuery(query.toString()).executeUpdate();
        return null;
    }
}
