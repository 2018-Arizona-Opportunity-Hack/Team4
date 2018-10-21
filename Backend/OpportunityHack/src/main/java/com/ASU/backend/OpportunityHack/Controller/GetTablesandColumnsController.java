package com.ASU.backend.OpportunityHack.Controller;

import com.ASU.backend.OpportunityHack.DAO.TablesandColumnsDAO;
import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import com.ASU.backend.OpportunityHack.Model.ObjectSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin
public class GetTablesandColumnsController {

    @Autowired
    TablesandColumnsDAO tablesandColumnsDAO;

    @RequestMapping(value = "/getTables", method = RequestMethod.GET)
    public List<String> getTables() {
        List<String> result = tablesandColumnsDAO.getTableNames();
        return result;
    }

    @RequestMapping(value = "/getTableColumns", method = RequestMethod.GET)
    public List<ObjectParameters> getTableColumns(@RequestParam(value = "tableName", required = true) String tableName) {
        List<ObjectParameters> result = tablesandColumnsDAO.getTableColumns(tableName);
        return result;
    }

    @RequestMapping(value = "/getTableColumnMapping", method = RequestMethod.GET)
    public List<ObjectSchema> getTableColumnMapping() {
        List<ObjectSchema> result = new ArrayList<>();
        List<String> tableNames = tablesandColumnsDAO.getTableNames();
        HashSet<String> hs = tablesandColumnsDAO.getEventsList();
        for (String tableName : tableNames) {
            ObjectSchema objectSchema = new ObjectSchema();
            objectSchema.setEntityName(tableName);
            objectSchema.setAttributes(tablesandColumnsDAO.getTableColumns(tableName));
            objectSchema.setIsEventTable(hs.contains(tableName));
            result.add(objectSchema);
        }
        return result;
    }

    @RequestMapping(value = "/getRelatedFieldColumns", method = RequestMethod.GET)
    public List<ObjectParameters> getRelatedFieldColumns(@RequestParam(value = "tableName") String tableName){
        List<Object> tables = tablesandColumnsDAO.getRelatedTables(tableName);
        tables.add(tableName);
        List<ObjectParameters> result = new ArrayList<>();
        for(Object table : tables){
            String convert = (String) table;
            List<ObjectParameters> list = tablesandColumnsDAO.getTableColumns(convert);
            for(ObjectParameters obj : list){
                obj.setName(convert+"."+obj.getName());
                result.add(obj);
            }
//            result.addAll();
        }
        return result;
    }
}
