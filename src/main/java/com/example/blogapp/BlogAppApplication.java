package com.example.blogapp;

import com.example.blogapp.config.Constants;
import com.example.blogapp.entities.Role;
import com.example.blogapp.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("123abc"));
        try {
            Role role = new Role();
            role.setId(Constants.ADMIN_USER);
            role.setName("ROLE_ADMIN");


            Role role2 = new Role();
            role2.setId(Constants.NORMAL_USER);
            role2.setName("ROLE_NORMAL");

            List<Role> roleList = List.of(role, role2);

            List<Role> roles = roleRepo.saveAll(roleList);

            roles.forEach(r -> System.out.println(r + r.getName()));

        } catch (Exception ex) {
            throw new Exception("Unable to create user");
        }
    }
}
