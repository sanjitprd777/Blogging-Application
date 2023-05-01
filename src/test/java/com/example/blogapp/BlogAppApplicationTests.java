package com.example.blogapp;

import com.example.blogapp.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void repoTest() {
        String name = this.userRepo.getClass().getName();
        String packageName = this.userRepo.getClass().getPackageName();
        System.out.println("Class name: " + name);
        System.out.println("Package name: " + packageName);
    }
}
