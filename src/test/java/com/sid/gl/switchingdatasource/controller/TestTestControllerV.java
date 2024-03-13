package com.sid.gl.switchingdatasource.controller;

import static io.unlogged.UnloggedTestUtils.*;
import static org.mockito.ArgumentMatchers.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.gl.switchingdatasource.entities.UserInternational;
import com.sid.gl.switchingdatasource.entities.UserNational;
import com.sid.gl.switchingdatasource.response.Response;
import com.sid.gl.switchingdatasource.service.TestService;
import java.lang.Exception;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public final class TestTestControllerV {
  private TestController testController;

  private TestService testService;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() throws Exception {
    testService = Mockito.mock(TestService.class);
    testController = new TestController();
    injectField(testController, "testService", testService);
  }

  @Test
  public void testGetResponseAll() throws Exception {
    // 
    List<UserInternational> allUserInternational = objectMapper.readValue("[{\"id\":1,\"name\":\"cheikh dieng\",\"age\":\"15\"},{\"id\":2,\"name\":\"mountary\",\"age\":\"16\"},{\"id\":3,\"name\":\"bamba dieng\",\"age\":\"18\"}]", new TypeReference<List<UserInternational>>(){});
    Mockito.when(testService.getAllUserInternational()).thenReturn(allUserInternational);
    // 
    // 
    List<UserNational> allUserNational = objectMapper.readValue("[{\"id\":1,\"name\":\"ranil\",\"age\":\"18\"},{\"id\":2,\"name\":\"anil\",\"age\":\"17\"},{\"id\":3,\"name\":\"sunil\",\"age\":\"16\"}]", new TypeReference<List<UserNational>>(){});
    Mockito.when(testService.getAllUserNational()).thenReturn(allUserNational);
    // 
    // Test candidate method [getResponseAll] [1920,1] - took 949ms
    Response response = testController.getResponseAll();
    Response responseExpected = objectMapper.readValue("{\"userNationals\":[{\"id\":1,\"name\":\"ranil\",\"age\":\"18\"},{\"id\":2,\"name\":\"anil\",\"age\":\"17\"},{\"id\":3,\"name\":\"sunil\",\"age\":\"16\"}],\"userInternationals\":[{\"id\":1,\"name\":\"cheikh dieng\",\"age\":\"15\"},{\"id\":2,\"name\":\"mountary\",\"age\":\"16\"},{\"id\":3,\"name\":\"bamba dieng\",\"age\":\"18\"}]}", Response.class);
    Assertions.assertEquals(responseExpected, response);
  }
}
