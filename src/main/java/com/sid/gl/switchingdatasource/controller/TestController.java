package com.sid.gl.switchingdatasource.controller;

import com.sid.gl.switchingdatasource.response.Response;
import com.sid.gl.switchingdatasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/all")
   public Response getResponseAll(){
       Response response = new Response();
       response.setUserInternationals(testService.getAllUserInternational());
       response.setUserNationals(testService.getAllUserNational());
       return response;
   }
}
