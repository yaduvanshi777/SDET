package Restassured;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostDifferentTest  {
    /** The
     * create request body method POST method
     * 1  POST HashMap
     * 2 using org.json
     * 3.using pojo(plain old java object)
     * using external json file
     *
     */


    // Using Hashmap
    //@Test
    void testpostusinghashmap(){
        HashMap data = new HashMap();
//        {
//            "id":"1",
//                "name":"Shyam",
//                "location": "US",
//                "ph":"98738467483",
//                "courses":["c++",
//                "restapi",

        data.put("name", "vivek");
        data.put("location", "IND");
        data.put("ph", "7206299779");
        String courseArray[] = {"c++", "restapi"};
        data.put("courses", courseArray);
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/student")
                .then()
                .statusCode(201)
                .body("name", equalTo("vivek"))
                .body("location", equalTo("IND"))
                .body("ph", equalTo("7206299779"))
                .body(
                        "courses[0]",
                        equalTo("c++"),
                        "courses[1]",
                        equalTo("restapi")
                )
                .header("Content-Type", "application/json")
                .log().all();
    }

    //delete student record
   // @Test(priority = 2 , dependsOnMethods = "testpostusinghashmap")
    void testdelete(){
        given()
                .when()
                .delete("http://localhost:3000/student/1")
                .then()
                .statusCode(200);
    }


//using org.json
 //   @Test
    void testpostusigorgson(){
        JSONObject data = new JSONObject();

        data.put("name", "vivekYaduvanshi");
        data.put("location", "HR");
        data.put("ph", "9206299779");

        String courseArra[] = {"ruby", "PYTHON"};
        data.put("courses", courseArra);



        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/student")


                .then()
                .statusCode(201)
                .body("name", equalTo("vivekYaduvanshi"))
                .body("location", equalTo("HR"))
                .body("ph", equalTo("9206299779"))
                .body(
                        "courses[0]",
                        equalTo("ruby"),
                        "courses[1]",
                        equalTo("PYTHON")
                )
                .header("Content-Type", "application/json")
                .log().all();
    }

    //delete student record
    @Test(priority = 2 , dependsOnMethods = "testpostusigorgson")
    void testdeleteorgjson(){
        given()
                .when()
                .delete("http://localhost:3000/student/1")
                .then()
                .statusCode(200);
    }



  //   using POJO class

    @Test(priority = 1)
    void testpostusigpojo(){

        pojo_PostRequest data = new pojo_PostRequest();
        data.setName("Jyoti");
        data.setLocation("HRI");
        data.setPh("9206299770");
        String courseArra[] = {"ruby", "postman"};
        data.setCourses(courseArra);






        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/student")


                .then()
                .statusCode(201)
                .body("name", equalTo("Jyoti"))
                .body("location", equalTo("HRI"))
                .body("ph", equalTo("9206299770"))
                .body(
                        "courses[0]",
                        equalTo("ruby"),
                        "courses[1]",
                        equalTo("postman")
                )
                .header("Content-Type", "application/json")
                .log().all();
    }

    //delete student record
    @Test(priority = 2 , dependsOnMethods = "testpostusigpojo")
    void testdeletepojojson(){
        given()
                .when()
                .delete("http://localhost:3000/student/1")
                .then()
                .statusCode(200);
    }




    @Test(priority = 1)
    void testpostusigexternaljsonfile() throws FileNotFoundException {

     //   File f = new File("/Users/vivekyaduvanshi/Downloads/seli/Learning/Body.json");
        File f = new File(".//Body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data =  new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/student")


                .then()
                .statusCode(201)
                .body("name", equalTo("lak"))
                .body("location", equalTo("IND"))
                .body("ph", equalTo("98338467483"))
                .body(
                        "courses[0]",
                        equalTo("c++"),
                        "courses[1]",
                        equalTo("postman")
                )
                .header("Content-Type", "application/json")
                .log().all();
    }

    //delete student record
    @Test(priority = 2 , dependsOnMethods = "testpostusigexternaljsonfile")
    void testdeleteexternaljson(){
        given()
                .when()
                .delete("http://localhost:3000/student/56b2")
                .then()
                .statusCode(200);
    }


}
