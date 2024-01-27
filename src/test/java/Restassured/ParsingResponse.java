package Restassured;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ParsingResponse {
    //http://restapi.adequateshop.com/api/Traveler
   // @Test
    void TestXMLResponse() {

        //Approch 1 below
//        given()
//                .when()
//                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
//                .then()
//                .headers("Content-Type", "application/xml; charset=utf-8")
//                .statusCode(200)
//                .body("TravelerinformationResponse.page", equalTo("1"))
//                .body("TravelerinformationResponse.travelers.Travelerinformation[1].name", equalTo("AS"));


        //Approch 2 below
        Response re = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(200, re.statusCode());
        Assert.assertEquals("application/xml; charset=utf-8", re.header("Content-Type"));

        re.getBody().prettyPrint(); // prettyPrint should return used xml string
        String pageno = re.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals("1", pageno);

        String travelername = re.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[1].name").toString();
        Assert.assertEquals("AS", travelername);
/* toString is used when we need to convert data to string
asString is used when we need to convert response to string
 */
    }

     @Test
     void testXmlResponse(){
         Response re = given()
                 .when()
                 .get("http://restapi.adequateshop.com/api/Traveler?page=1");

            XmlPath xmlPath = new XmlPath(re.asString()); // response in string
         //total no of tarveller
         List<String> travellers = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.size()");
         System.out.println(travellers); // total    no of tarveller for 1 page

         List<String> travelers = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
         Assert.assertEquals(travelers.size(),10);

         List<String> travelerNames = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
         Assert.assertEquals(travelerNames.size(),10);

boolean status = false;
         // If you want to print each traveler name individually
         for (String travelerName : travelerNames) {

             // approch 1 for assertion
            // System.out.println(travelerName);
//             if(travelerName.equals("AS")) {
//                 Assert.assertEquals(travelerName, "AS");
//             }

             if(travelerName.equals("AS"))
             {
                 status = true;
                 break;
             }
         }
         Assert.assertEquals(status, true);
    }

}

