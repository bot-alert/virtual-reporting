package com.virtual.reporting.configuration;

import com.virtual.reporting.entity.Role;
import com.virtual.reporting.entity.User;
import com.virtual.reporting.repository.RoleRepository;
import com.virtual.reporting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.virtual.reporting.constant.VirtualReportingConstant.ROLE_ADMIN;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final ApplicationConfigurationProperty appProperty;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationListener<ApplicationStartedEvent> applicationStartedEventApplicationListener() {
        return event -> {
            Role role = roleRepository.findByName(ROLE_ADMIN).orElseGet(() -> {
                Role adminRole = new Role();
                adminRole.setName(ROLE_ADMIN);
                roleRepository.save(adminRole);
                return adminRole;
            });
            Optional<User> user = userRepository.findByEmail(appProperty.superuser());
            if (user.isEmpty()) {
                User superUser = new User();
                superUser.setName("Super User");
                superUser.setEmail(appProperty.superuser());
                superUser.setPassword(passwordEncoder.encode(appProperty.password()));
                superUser.setRole(role);
                userRepository.save(superUser);
            }
        };
    }

}
