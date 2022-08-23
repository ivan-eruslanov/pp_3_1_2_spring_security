package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RolesRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user, String[] selectedRoles) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(selectedRoles)
                .forEach(a -> roles.add(rolesRepository.findRoleByRole(a)));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User oldUser, User newUser, String[] selectedRoles) {
        Optional<User> oUser = Optional.of(newUser);
        oUser.get().setPassword(oldUser.getPassword());
        oUser.get().setEmail(oldUser.getEmail());
        oUser.get().setName(oldUser.getName());
        Set<Role> roles = new HashSet<>();
        Arrays.stream(selectedRoles)
                .forEach(x -> roles.add(rolesRepository.findRoleByRole(x)));
        oUser.get().setRoles(roles);
        userRepository.save(oUser.get());
    }

    @Transactional
    public void deleteById(Long id) {       //удаление юзера по id
        userRepository.deleteById(id);
    }

    public User getUserByName(String name) {    //поиск по имени
        return userRepository.getUserByName(name);
    }
}
