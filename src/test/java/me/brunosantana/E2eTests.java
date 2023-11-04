package me.brunosantana;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class E2eTests {

    public static final String API_URL = System.getenv("API_URL");

    @Test
    public void shouldGetAllProducts() {
        given()
        .when()
            .get(API_URL.concat("/products"))
        .then()
            .statusCode(200)
            .body("$", hasSize(3))
            .body("name", hasItems("Livro Deitel Java Como Programar", "Livro TDD da Casa do Código", "Livro Flexible Dieting"))
            .body("price", contains(199.5F, 50F, 90F))
            .body("price[2]", is(90F));
    }

    @Test
    public void shouldGetProductWithId2() {
        given()
        .when()
            .get(API_URL.concat("/products/2"))
        .then()
            .statusCode(200)
            .body("id", is(2))
            .body("name", is("Livro TDD da Casa do Código"))
            .body("description", is("livro top"))
            .body("price", is(50F))
            .body("createdAt", is("2023-11-01T21:43:20.536+00:00"))
            .body("updatedAt", is("2023-11-01T21:43:20.536+00:00"));
    }

}
