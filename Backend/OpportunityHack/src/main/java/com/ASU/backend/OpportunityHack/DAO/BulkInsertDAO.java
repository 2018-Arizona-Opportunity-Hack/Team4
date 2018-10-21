package com.ASU.backend.OpportunityHack.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class BulkInsertDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Map<String, Object> insertCSV(String tableName, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        StringBuilder query = new StringBuilder("insert into "+tableName+" (");
        List<String> list = Arrays.asList(line.split(","));
        for(String str : list){
           String[] stringArray = str.split("\\(");
           query.append(stringArray[0]);
           query.append(",");
        }
        query.setLength(query.length()-1);
        query.append(") ");
        query.append("values");
        query.append(" ");
        while((line = bufferedReader.readLine()) != null){
            if(!line.isEmpty()){
                List<String> row = Arrays.asList(line.split(","));
                query.append("(");
                for(String r : row){
                    query.append(r);
                    query.append(",");
                }
                query.setLength(query.length()-1);
                query.append(")");
                query.append(",");
            }
        }
        query.setLength(query.length()-1);
        System.out.println(query);
        entityManager.createNativeQuery(query.toString()).executeUpdate();
        return null;
    }
}
