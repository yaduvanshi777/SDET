package Restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LogingTest {
    @Test
    void Testlogs(){
        given()
               .when()
               .get("https://reqres.in/api/users?page=2")
               .then()
               .log().body()
                .log().headers();



    }
}
