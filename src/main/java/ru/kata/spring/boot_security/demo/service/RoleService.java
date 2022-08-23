package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RolesRepository;

import java.util.List;

@Service
public class RoleService {
    private final RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List <Role> getRoles() {
        return rolesRepository.findAll();
    }
}
