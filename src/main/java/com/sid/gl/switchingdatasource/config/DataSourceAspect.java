package com.sid.gl.switchingdatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(-10)
public class DataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Pointcut("@annotation(com.sid.gl.switchingdatasource.config.SwitchDataSource)")
    public void annotationPointCut(){

    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);
        if(annotation!=null){
            AbstractRoutingDataSourceImpl.setDatabaseName(annotation.value());
            logger.info("Switch datasource to [{}] in method [{}]",
                    annotation.value(),joinPoint.getSignature());

        }
    }

    //restore to default method after execution of the method
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        if(null !=AbstractRoutingDataSourceImpl.getDatabaseName()){
            AbstractRoutingDataSourceImpl.removeDatabaseName();
        }
    }
}
