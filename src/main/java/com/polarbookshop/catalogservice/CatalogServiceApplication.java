package com.polarbookshop.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//Configuration помечает класс как источник определений bean-компонентов.
//@ComponentScan включает сканирование компонентов для автоматического поиска и регистрации bean-компонентов в контексте Spring.
//@EnableAutoConfiguration включает возможности автоматической настройки, предлагаемые Spring Boot.
@SpringBootApplication//Определяет класс конфигурации Spring и запускает сканирование компонентов и автоматическую настройку Spring Boot
@ConfigurationPropertiesScan//Загружает компоненты данных конфигурации в контексте Spring. Вместо того чтобы заставлять Spring сканировать контекст приложения в поисках компонентов данных конфигурации, вы можете напрямую указать, какие из них Spring должен учитывать
public class CatalogServiceApplication {

    public static void main(String[] args) {//Метод, используемый для запуска приложения. Он регистрирует текущий класс для запуска на этапе начальной загрузки приложения.
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
