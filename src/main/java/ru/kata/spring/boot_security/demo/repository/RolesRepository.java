package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


/**
 * @Query - аннотация означает, что в случае вызова текущего метода будет выполнен запрос к базе данных, запрос указан в параметрах;
 */

public interface RolesRepository extends JpaRepository<Role, Long> {
     //формирование интерфейса с наследованием от JpaRepository

    Role findRoleByRole(String role);
   //указание по какому параметру искать в БД
}
