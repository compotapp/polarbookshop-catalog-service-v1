package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//Предоставляет настройку для тестирования приложений Spring Boot.
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
//Определяет тестовый пример
    void contextLoads() {//Пустой тест, используемый для проверки правильности загрузки контекста приложения.
    }

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());
                });
    }
}