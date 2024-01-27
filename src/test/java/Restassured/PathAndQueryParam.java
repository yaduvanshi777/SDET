package Restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParam {

    //https://reqres.in/api/users?page=2&id=5
    @Test
    void testPathAndQueryParam(){
        given()
              .pathParam("mypath", "user") //path parameter
                .queryParam("page", "2") //query parameter
                .queryParam("id", "5")//query parameter

              .when()
              .get("https://reqres.in/api/{mypath}")

              .then()
              .statusCode(200)
                .log().all();


    }

}
