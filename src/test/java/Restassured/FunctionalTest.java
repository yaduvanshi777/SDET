package Restassured;


import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FunctionalTest {
    int id;
    /**
     *
     *
     * given()  function
     * content type , set cookie, add authentication, add parameters, set headers information.
     *
     * when() function
     * get ,post ,put ,delete
     *
     * then() function
     * validate status code ,extract headers information ,extract cookies information ,extract response body..
     *
     * and() function is also in then   function
     *
     */
    @Test
    void getuser() {



        given()
                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createuser(){


        HashMap  data = new HashMap();
        data.put("name" , "vivek");
        data.put("job", " software engineer");


        id=given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();

    }
    // this above id use in update request in below



//    {
//        "name": "vivek",
//            "job": " software engineer",
//            "id": "895",
//            "createdAt": "2024-01-20T13:16:22.268Z"
//    }

    @Test(priority = 3, dependsOnMethods = {"createuser"})
    void updateuser(){

        HashMap  data = new HashMap();
        data.put("name" , "vivek");
        data.put("job", " software engineer in paytm");
//        data.put("id",id);
        //data.put("createdAt", "2024-01-20T13:16:22.268Z");


        given()
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(200);
        //.log().all();



    }

    @Test(priority = 4, dependsOnMethods = {"updateuser"})
    void deleteuser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();
    }

}
