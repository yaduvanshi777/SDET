package Restassured;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class HeaderTest {
   // @Test
    void getHeaders() {

        given()
                .when()
                .get("https://www.google.com")
                .then()
                .headers("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .headers("Content-Encoding", "gzip")
                .and()
                .headers("Server", "gws")
                .log().all();
    }
@Test
        void getHeadersinfo(){

            Response res = given()

                    .when()
                    .get("https://www.google.com");

                    //get Single Headers info
        System.out.println(res.header("Content-Type"));
        System.out.println(res.header("Content-Encoding"));
        System.out.println(res.header("Server"));

        //get all Headers info
        System.out.println(res.headers());

    Headers headers = res.getHeaders();
    for(Header header : headers)
    {
        System.out.println(header.getName()+"=========="+header.getValue());
    }

    }
}
