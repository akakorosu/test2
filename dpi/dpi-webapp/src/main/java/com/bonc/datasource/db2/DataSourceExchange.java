package com.bonc.datasource.db2;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.bonc.common.annotation.TargetDataSource;

public class DataSourceExchange implements MethodBeforeAdvice, AfterReturningAdvice {
 
     @Override
     public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
         DataSourceHolder.clearDataSource();
     }
 
     @Override
     public void before(Method method, Object[] args, Object target) throws Throwable {
         // 这里TargetDataSource是自定义的注解
         if (method.isAnnotationPresent(TargetDataSource.class)) {
             TargetDataSource datasource = method.getAnnotation(TargetDataSource.class);
             DataSourceHolder.setDataSource(datasource.name());
         } else {
             if(target.getClass().isAnnotationPresent(TargetDataSource.class))
             {
                 TargetDataSource datasource = target.getClass().getAnnotation(TargetDataSource.class);
                 DataSourceHolder.setDataSource(datasource.name());
             }
         }
 
     }
 } 