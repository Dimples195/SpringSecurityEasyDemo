package com.tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启授权注解功能
public class SpringSecurityFastApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityFastApplication.class,args);
        System.out.println("----程序启动成功----");
    }
}
