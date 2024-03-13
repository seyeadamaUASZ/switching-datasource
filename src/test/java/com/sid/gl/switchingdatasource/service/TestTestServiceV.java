package com.sid.gl.switchingdatasource.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.gl.switchingdatasource.entities.UserInternational;
import com.sid.gl.switchingdatasource.repositories.UserInternationalRepository;
import java.lang.Exception;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static io.unlogged.UnloggedTestUtils.*;

import com.sid.gl.switchingdatasource.entities.UserNational;
import com.sid.gl.switchingdatasource.repositories.UserNationalRepository;

public final class TestTestServiceV {

    private TestService testService;

    private UserInternationalRepository userInternationalRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() throws Exception {
        userInternationalRepository = Mockito.mock(UserInternationalRepository.class);
        testService = new TestService();
        injectField(testService, "userInternationalRepository", userInternationalRepository);
        userNationalRepository = Mockito.mock(UserNationalRepository.class);
        injectField(testService, "userNationalRepository", userNationalRepository);
    }

    @Test
    public void testGetAllUserInternational() throws Exception {
        // 
        List<UserInternational> findAllResult = objectMapper.readValue("[{\"id\":1,\"name\":\"cheikh dieng\",\"age\":\"15\"},{\"id\":2,\"name\":\"mountary\",\"age\":\"16\"},{\"id\":3,\"name\":\"bamba dieng\",\"age\":\"18\"}]", new TypeReference<List<UserInternational>>() {
        });
        Mockito.when(userInternationalRepository.findAll()).thenReturn(findAllResult);
        // 
        // Test candidate method [getAllUserInternational] [1937,1] - took 703ms
        List<UserInternational> results = testService.getAllUserInternational();
        //List findAllResultExpected = objectMapper.readValue("[{\"id\":1,\"name\":\"cheikh dieng\",\"age\":\"15\"},{\"id\":2,\"name\":\"mountary\",\"age\":\"16\"},{\"id\":3,\"name\":\"bamba dieng\",\"age\":\"18\"}]", List.class);
        Assertions.assertEquals(findAllResult, results);
        Assertions.assertEquals(3, results.size());
    }

    private UserNationalRepository userNationalRepository;

    @Test
    public void testGetAllUserNational() throws Exception {
        // 
        List<UserNational> findAllResult = objectMapper.readValue("[{\"id\":1,\"name\":\"ranil\",\"age\":\"18\"},{\"id\":2,\"name\":\"anil\",\"age\":\"17\"},{\"id\":3,\"name\":\"sunil\",\"age\":\"16\"}]", new TypeReference<List<UserNational>>() {
        });
        Mockito.when(userNationalRepository.findAll()).thenReturn(findAllResult);
        // 
        // Test candidate method [getAllUserNational] [2363,1] - took 241ms
        List<UserNational> results = testService.getAllUserNational();
        //List<UserInternational> findAllResultExpected = objectMapper.readValue("[{\"id\":1,\"name\":\"ranil\",\"age\":\"18\"},{\"id\":2,\"name\":\"anil\",\"age\":\"17\"},{\"id\":3,\"name\":\"sunil\",\"age\":\"16\"}]", List.class);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(3,results.size());
        Assertions.assertEquals(findAllResult,results);
    }
}
