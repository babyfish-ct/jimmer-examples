package org.babyfish.jimmer.example.cloud.author;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableImplicitApi
@SpringBootApplication
public class AuthorServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthorServiceApp.class, args);
    }
}
