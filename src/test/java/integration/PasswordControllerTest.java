//package integration;
//
//import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.notNullValue;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PasswordControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    public void setup() {
//        RestAssured.port = port;
//    }
//
//    @Test
//    public void testCreatePassword() {
//        String createPAsswordJSON = "{\n" +
//                "    \"description\": \"Banco Santander - Conta Corrente\",\n" +
//                "    \"url\": \"https://www.santander.com.br\",\n" +
//                "    \"username\": \"123456\",\n" +
//                "    \"password\": \"123456\",\n" +
//                "    \"notes\": \"\",\n" +
//                "    \"userId\": 1\n" +
//                "}";
//        RestAssured.given()
//                .contentType(io.restassured.http.ContentType.JSON)
//                .body(createPAsswordJSON)
//                .post("/passwords")
//                .then()
//                .statusCode(201)
//                .body("id", notNullValue())
//                .body("description", equalTo("Banco Santander - Conta Corrente"))
//                .body("url", equalTo("https://www.santander.com.br"))
//                .body("username", equalTo("123456"))
//                .body("password", notNullValue())
//                .body("notes", equalTo(""))
//                .body("userId", notNullValue());
//    }
//}
//TODO: Implementar testes de integração para o controller de Password