package com.example.connectionlifecycle.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Aspect
@Component
public class DataSourceAspect {
    @Around("target(javax.sql.DataSource)")
    public Object dataSourceAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("Method Invoked::"+proceedingJoinPoint
                .getSignature().getName());

        Object obj = proceedingJoinPoint.proceed();
        if (obj instanceof Connection){
            return createConnectionProxy((Connection) obj);
        }
        return obj;
    }

    private Connection createConnectionProxy(Connection connection){
        return(Connection) Proxy.newProxyInstance(
                DataSourceAspect.class.getClassLoader(),
                new Class[]{Connection.class},
                new ConnectionInvocationHandler(connection)
        );
    }
}
