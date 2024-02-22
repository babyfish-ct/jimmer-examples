package org.babyfish.jimmer.example.cloud.store;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableImplicitApi
@SpringBootApplication
public class StoreServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(StoreServiceApp.class, args);
    }
}
