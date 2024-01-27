package Restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonResponseData {
    //@Test(priority = 1)
    void TestJsonResponse() {

        // samll json file is used for this test case

        given()
                .contentType("application/json")

                .when()
                .get("http://localhost:3000/store")

                .then()
                .statusCode(200)
                .headers("Content-Type", "application/json")
                .body("book[2].title", equalTo("ficition"));


//        {
//            "author": "herman",
//                "category": "ficition",
//                "price": "350.30",
//                "title": "ficition"
//        }
    }

    // Approch 2


        @Test
        void TestjsonResponsewithnew(){

            Response res = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("http://localhost:3000/store");
            // captuing the data response from json file

            Assert.assertEquals(200, res.statusCode());

           /* Assert.assertEquals("application/json", res.header("Content-Type"));
           // Assert.assertEquals("herman", res.jsonPath().get("author"));
            String bookname = res.jsonPath().get("book[2].title").toString();
            Assert.assertEquals("ficition", bookname);*/


            JSONObject jo = new JSONObject(res.asString());//convert to JSONObject type
            //System.out.println(jo);



//            for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
//
//               String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString(); // get the title of valueOf(i));
//                System.out.println(bookTitle);
//                }

            // findthe booktitle in the reposne

            boolean status = false;
            for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

               String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString(); // get the title of valueOf(i));
               if(bookTitle.equals("ficition")){
                   status = true;
                   break;
               }

            } Assert.assertEquals(status, true);

            // find the total price of the books
            double totalPrice = 0;
            for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

               String bookPrice = jo.getJSONArray("book").getJSONObject(i).get("price").toString(); // get the price of valueOf(i));
               totalPrice += Double.parseDouble(bookPrice);

            }
            System.out.println(totalPrice);
            Assert.assertEquals(totalPrice, 700.90,0.001); // 0.001 is the precision value

        }
    }

