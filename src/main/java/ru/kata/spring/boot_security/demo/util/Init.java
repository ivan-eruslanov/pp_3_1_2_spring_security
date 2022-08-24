package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Init(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void createTestUsersWithRoles() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleRepository.save(role1);
        roleRepository.save(role2);

        User user1 = new User
                ("user", "Иван", "Иванов",
                        (byte) 34, "user@mail.ru", "$2a$12$SbrlyMjtF2vx/i1tbD0qLOdKBFbr1A5GxvI/izG4t9gBVRY1OgfeG"); // 200
        User user2 = new User
                ("admin", "Алексей", "Сидоров",
                        (byte) 24, "admin@mail.ru", "$2a$12$Djm7UfaK//Iw6j.NntGh3.kXlRONwNxF0Fd3DQ9pI.RS6IVqrhFpm"); // 100

        user1.setRoles(new HashSet<>(Set.of(role2)));
        user2.setRoles(new HashSet<>(Set.of(role1)));

        userRepository.save(user1);
        userRepository.save(user2);

    }
}
