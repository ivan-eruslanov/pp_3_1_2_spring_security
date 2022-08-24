package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT c FROM User c WHERE c.username = ?1")
    User getUserByUsername(String username);
}
