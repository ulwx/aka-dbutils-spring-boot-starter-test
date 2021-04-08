package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestApplication.class);
        application.run(args);;
    }

   @Bean
   public ApplicationRunner runner(MyService myService){

        return args -> {
            myService.testMapper();
             myService.updateMdb();
            System.out.println("ok!");
        };
    }

}
