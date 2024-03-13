package com.sid.gl.switchingdatasource.service;

import com.sid.gl.switchingdatasource.config.SwitchDataSource;
import com.sid.gl.switchingdatasource.entities.UserInternational;
import com.sid.gl.switchingdatasource.entities.UserNational;
import com.sid.gl.switchingdatasource.repositories.UserInternationalRepository;
import com.sid.gl.switchingdatasource.repositories.UserNationalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private UserNationalRepository userNationalRepository;

    @Autowired
    private UserInternationalRepository userInternationalRepository;

    @SwitchDataSource(value = "national")
    public List<UserNational> getAllUserNational(){
        return userNationalRepository.findAll();
    }
    @SwitchDataSource(value = "international")
    public List<UserInternational> getAllUserInternational(){
        return userInternationalRepository.findAll();
    }
}
