package com.ASU.backend.OpportunityHack.Controller;

import com.ASU.backend.OpportunityHack.DAO.TablesandColumnsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetTablesandColumnsController {

    @Autowired
    TablesandColumnsDAO tablesandColumnsDAO;

    @RequestMapping(value = "/getTables", method = RequestMethod.GET)
    public List<String> getTables(){
        List<String> result = tablesandColumnsDAO.getTableNames();
        return result;
    }

    @RequestMapping(value = "/getTableColumns", method = RequestMethod.GET)
    public List<String> getTableColumns(@RequestParam(value = "tableName", required = true) String tableName){
        List<String> result = tablesandColumnsDAO.getTableNames();
        return result;
    }
}
