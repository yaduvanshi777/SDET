package Restassured;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CokiesTest {

    //@Test
    void getCookies(){

        given()
                .when()
                .get("https://www.google.com")
                .then()
                .cookie("AEC","Ae3NU9OWp_drXVTKlIMX0z40A9i55R_WzJT_F76--i4-W0qH3fPuf8wPaA")
                .log().all();

    }

    @Test(priority = 2)
    void getCookiesinfo(){

        Response res = given()

                .when()
                .get("https://www.google.com");

        //get single cookie info
        System.out.println(res.cookie("AEC"));
        System.out.println("value of cookie " + res.cookie("AEC"));

        //get all cookie info
        System.out.println(res.cookies());

        Map<String,String> cookies = res.getCookies();
        System.out.println(cookies.keySet());  // get key of cookies

        for(String k : cookies.keySet())
        {
            String cookie = res.getCookie(k);
            System.out.println(k+"="+cookie); // get value of cookie with key

        }
    }
}
