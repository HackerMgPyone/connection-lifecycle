package com.example.connectionlifecycle;

import com.example.connectionlifecycle.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class ConnectionLifecycleApplication {

    private final EmployeeService employeeService;

    @Bean
    public ApplicationRunner runner(){
        return runner -> {
            employeeService.withTransaction();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectionLifecycleApplication.class, args);
    }

}
