package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user, String[] selectedRoles) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(selectedRoles)
                .forEach(a -> roles.add(roleRepository.findRoleByRole(a)));
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(User oldUser, User newUser, String[] selectedRoles) {
        Optional<User> oUser = Optional.of(newUser);
        oUser.get().setPassword(oldUser.getPassword());
        oUser.get().setEmail(oldUser.getEmail());
        oUser.get().setUsername(oldUser.getUsername());
        oUser.get().setFirstName(oldUser.getFirstName());
        oUser.get().setLastName(oldUser.getLastName());
        oUser.get().setAge(oldUser.getAge());
        Set<Role> roles = new HashSet<>();
        Arrays.stream(selectedRoles)
                .forEach(x -> roles.add(roleRepository.findRoleByRole(x)));
        oUser.get().setRoles(roles);
        userRepository.save(oUser.get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.getUserByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User for '%s' not exists ", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
