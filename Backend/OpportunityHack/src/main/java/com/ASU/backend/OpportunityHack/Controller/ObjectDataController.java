package com.ASU.backend.OpportunityHack.Controller;

import com.ASU.backend.OpportunityHack.DAO.CreateDataDAO;
import com.ASU.backend.OpportunityHack.DAO.TablesandColumnsDAO;
import com.ASU.backend.OpportunityHack.Model.ObjectData;
import com.ASU.backend.OpportunityHack.Model.ObjectParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
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
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + entity + "-template-" + fileSuffix + ".csv\"");
        List<ObjectParameters> objectParametersList = tablesandColumnsDAO.getTableColumns(entity);
        StringBuilder columnNames = new StringBuilder();
        StringBuilder dataTypes = new StringBuilder();
        StringBuilder mandatory = new StringBuilder();
        //StringBuilder unique = new StringBuilder();
        for (ObjectParameters op : objectParametersList) {
            if (objectParametersList.indexOf(op) != objectParametersList.size() - 1) {
                columnNames.append(op.getName() + "(" + mapData(op.getDataType()) + "-" + mapMandatory(op.getIsMandatory()) + ")" + ",");
                //dataTypes.append(mapData(op.getDataType()) + ",");
                //mandatory.append(mapMandatory(op.getMandatory()) + ",");
                //unique.append(mapUnique(op.getUnique()) + ",");
            } else {
                columnNames.append(op.getName() + "(" + mapData(op.getDataType()) + "-" + mapMandatory(op.getIsMandatory()) + ")");
                //dataTypes.append(mapData(op.getDataType()));
                //mandatory.append(mapMandatory(op.getMandatory()));
                //unique.append(op.getUnique());
            }
        }
        columnNames.append("\n");
        //dataTypes.append("\n");
        //mandatory.append("\n");
        //unique.append("\n");
        StringBuilder data = new StringBuilder();
        data.append(columnNames);
        //data.append(dataTypes);
        //data.append(mandatory);
        //data.append(unique);
        return data.toString();
    }

    private String mapMandatory(Boolean mandatory) {
        if (mandatory) {
            return "Mandatory";
        }
        return "Not-Mandatory";
    }

    @RequestMapping(value = "/export/{entity}")
    @ResponseBody
    public String exportEntity(@PathVariable String entity, HttpServletResponse response) {
        response.setContentType("application/csv");
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + entity + "-export-" + fileSuffix + ".csv\"");
        return tablesandColumnsDAO.getTableDataToExport(entity);
    }

//    private String mapUnique(boolean u) {
//        if (u == null) {
//            return "not-unique";
//        } else {
//            return "unique";
//        }
//    }

    private String mapData(String dataType) {
        if (dataType.toLowerCase().contains("integer")) {
            return "Number";
        } else if (dataType.toLowerCase().contains("varchar")) {
            return "Text";
        } else if (dataType.toLowerCase().contains("timestamp")) {
            return "Date";
        } else {
            return "invalid";
        }
    }


}