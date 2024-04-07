package com.stanley.dodospring;

import com.stanley.dodospring.domain.UserRole;
import com.stanley.dodospring.domain.entities.UserEntity;
import com.stanley.dodospring.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class DoDoSpringBootEditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoDoSpringBootEditionApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            userRepository.save(new UserEntity("admin", "admin@local.host", "admin", UserRole.ROLE_ADMIN));
            userRepository.save(new UserEntity("user", "user@local.host", "user", UserRole.ROLE_USER));
        };
    }

}
