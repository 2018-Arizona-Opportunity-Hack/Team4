package com.ASU.backend.OpportunityHack.Controller;

import com.ASU.backend.OpportunityHack.DAO.CreateDataDAO;
import com.ASU.backend.OpportunityHack.DAO.TablesandColumnsDAO;
import com.ASU.backend.OpportunityHack.Model.ObjectData;
import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ObjectDataController {
    @Autowired
    CreateDataDAO createDataDAO;
    @Autowired
    TablesandColumnsDAO tablesandColumnsDAO;

    @PostMapping(path = "/save")
    public String saveObject(@RequestBody ObjectData od) {
        createDataDAO.saveObject(od);
        return "ok";
    }

    @RequestMapping(value = "/download-template/{entity}")
    @ResponseBody
    public String templateAsCSV(@PathVariable String entity, HttpServletResponse response) {
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + entity + ".csv\"");
        List<ObjectParameters> objectParametersList = tablesandColumnsDAO.getTableColumns(entity);
        StringBuilder columnNames = new StringBuilder();
        StringBuilder dataTypes = new StringBuilder();
        StringBuilder mandatory = new StringBuilder();
        //StringBuilder unique = new StringBuilder();
        for (ObjectParameters op : objectParametersList) {
            if (objectParametersList.indexOf(op) != objectParametersList.size() - 1) {
                columnNames.append(op.getName() + ",");
                dataTypes.append(mapData(op.getDataType()) + ",");
                mandatory.append(mapMandatory(op.getMandatory()) + ",");
                //unique.append(mapUnique(op.getUnique()) + ",");
            } else {
                columnNames.append(op.getName());
                dataTypes.append(mapData(op.getDataType()));
                mandatory.append(mapMandatory(op.getMandatory()));
                //unique.append(op.getUnique());
            }
        }
        columnNames.append("\n");
        dataTypes.append("\n");
        mandatory.append("\n");
        //unique.append("\n");
        StringBuilder data = new StringBuilder();
        data.append(columnNames);
        data.append(dataTypes);
        data.append(mandatory);
        //data.append(unique);
        return data.toString();
    }

    private String mapMandatory(Boolean mandatory) {
        if (mandatory) {
            return "Mandatory";
        }
        return "Not-Mandatory";
    }

//    private String mapUnique(boolean u) {
//        if (u == null) {
//            return "not-unique";
//        } else {
//            return "unique";
//        }
//    }

    private String mapData(String dataType) {
        if (dataType.equals("int")) {
            return "number";
        } else if (dataType.equals("str")) {
            return "text";
        } else if (dataType.equals("date")) {
            return "Date";
        } else {
            return "invalid";
        }
    }


}