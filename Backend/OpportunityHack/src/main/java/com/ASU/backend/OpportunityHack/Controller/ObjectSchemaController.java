package com.ASU.backend.OpportunityHack.Controller;


import com.ASU.backend.OpportunityHack.DAO.CreateObjectDAO;
import com.ASU.backend.OpportunityHack.Model.ObjectSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ObjectSchemaController {

    @Autowired
    CreateObjectDAO createObjectDAO;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String helloWorld(){
        return "HelloWorld!";
    }

    @RequestMapping(value = "/objectSchema", method = RequestMethod.POST)
    public Map<String, Object> createObject(@RequestBody ObjectSchema objectSchema){
        System.out.println(objectSchema.getTableName());
        createObjectDAO.createObject(objectSchema);
        return null;
    }
}
