package com.ASU.backend.OpportunityHack.Controller;

import com.ASU.backend.OpportunityHack.Attribute;
import com.ASU.backend.OpportunityHack.DAO.CreateDataDAO;
import com.ASU.backend.OpportunityHack.Model.ObjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ObjectDataController {
    @Autowired
    CreateDataDAO createDataDAO;

    @GetMapping(path = "/test")
    public ObjectData test() {
        ArrayList<Attribute> al = new ArrayList<>();
        al.add(new Attribute("id", "123"));
        return new ObjectData("donor", al);
    }

    @PostMapping(path = "/save")
    public String saveObject(@RequestBody ObjectData od) {
        createDataDAO.saveObject(od);
        return "ok";
    }


}