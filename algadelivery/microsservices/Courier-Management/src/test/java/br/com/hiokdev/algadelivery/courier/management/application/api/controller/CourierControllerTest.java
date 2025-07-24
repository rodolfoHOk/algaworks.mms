package br.com.hiokdev.algadelivery.courier.management.application.api.controller;

import br.com.hiokdev.algadelivery.courier.management.application.dto.CourierInput;
import br.com.hiokdev.algadelivery.courier.management.domain.model.Courier;
import br.com.hiokdev.algadelivery.courier.management.domain.repository.CourierRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourierControllerTest {

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CourierRepository courierRepository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/couriers";
    }

    @Test
    void shouldReturn201() throws JsonProcessingException {
        CourierInput courierInput = new CourierInput();
        courierInput.setName("João da Silva");
        courierInput.setPhone("(11) 95555-1111");
        String requestBody = objectMapper.writeValueAsString(courierInput);

        RestAssured
                .given()
                    .body(requestBody)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalTo(courierInput.getName()));
    }

    @Test
    void shouldReturn200() {
        Courier courierToPersist = Courier.brandNew("Maria Souza", "(11) 95555-2222");
        Courier savedCourier = courierRepository.saveAndFlush(courierToPersist);

        RestAssured
                .given()
                    .pathParam("courierId", savedCourier.getId())
                    .accept(ContentType.JSON)
                .when()
                    .get("/{courierId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", Matchers.equalTo(savedCourier.getId().toString()))
                    .body("name", Matchers.equalTo(courierToPersist.getName()))
                    .body("phone", Matchers.equalTo(courierToPersist.getPhone()));

    }

}
