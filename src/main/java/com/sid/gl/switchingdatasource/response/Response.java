package com.sid.gl.switchingdatasource.response;

import com.sid.gl.switchingdatasource.entities.UserInternational;
import com.sid.gl.switchingdatasource.entities.UserNational;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<UserNational> userNationals;
    List<UserInternational> userInternationals;
}
