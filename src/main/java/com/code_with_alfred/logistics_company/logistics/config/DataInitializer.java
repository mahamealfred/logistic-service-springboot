package com.code_with_alfred.logistics_company.logistics.config;


import com.code_with_alfred.logistics_company.logistics.entity.Role;
import com.code_with_alfred.logistics_company.logistics.entity.User;
import com.code_with_alfred.logistics_company.logistics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (userRepository.findByEmail("admin@logistics.com").isEmpty()) {
            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@logistics.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .phoneNumber("+1234567890")
                    .build();
            userRepository.save(admin);
            System.out.println("Admin user created: admin@logistics.com / admin123");
        }

        // Create sample driver if not exists
        if (userRepository.findByEmail("driver@logistics.com").isEmpty()) {
            User driver = User.builder()
                    .firstName("John")
                    .lastName("Driver")
                    .email("driver@logistics.com")
                    .password(passwordEncoder.encode("driver123"))
                    .role(Role.DRIVER)
                    .phoneNumber("+1234567891")
                    .build();
            userRepository.save(driver);
            System.out.println("Driver user created: driver@logistics.com / driver123");
        }
    }
}