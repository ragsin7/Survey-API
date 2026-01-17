package com.propsur.api.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = {"com.propsur.api.project", "com.propsur.main"})
@EntityScan(basePackages = "com.propsur.api.project.entity")
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@Slf4j
public class JwtProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtProjectApplication.class, args);
        log.info(":: PROPERTY SURVEY Application API ::        (v1.4.RELEASE)");
		log.info(":: JAVA Version       ::        ("+System.getProperty("java.version")+")");
		log.info(":: Author -->  ::   Raghav Singh Running");
    }
}

