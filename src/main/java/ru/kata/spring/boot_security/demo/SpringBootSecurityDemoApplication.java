package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @SpringBootApplication - используется для:
 *  1) включения механизма автоконфигурации Spring Boot;
 *  2) включения сканирования @Component для пакета, в котором находится приложение;
 *  3) позволяет регистрировать дополнительные компоненты (beans) в контексте или импортировать дополнительные классы конфигурации;
 */

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

}
