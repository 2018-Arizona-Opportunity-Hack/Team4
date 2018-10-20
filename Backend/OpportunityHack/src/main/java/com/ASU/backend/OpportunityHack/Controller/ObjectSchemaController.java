package com.ASU.backend.OpportunityHack.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectSchemaController {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String helloWorld(){
        return "HelloWorld!";
    }
}
