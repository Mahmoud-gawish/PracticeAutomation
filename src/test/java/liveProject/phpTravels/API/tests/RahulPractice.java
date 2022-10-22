package liveProject.phpTravels.API.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import liveProject.phpTravels.API.tests.files.Payload;
import liveProject.phpTravels.API.tests.files.ReUsableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static  io.restassured.RestAssured.*;
public class RahulPractice {


  //given - all input details
    // when - submit the api - resource, http method
    // Then - validate the response

    @Test
    public void test1(){

        // add place then update place with new address >> get place validate if new address is present in the responce
        baseURI = "https://rahulshettyacademy.com";
      String responce =   given().log().all().
                queryParam("Key","qaclick123").
                header("Content-Type","application/json").
                body(Payload.AddPlace()).when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).
                body("scope",equalTo("APP")).
                header("Server",equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();

        System.out.println(responce);
        JsonPath js = new JsonPath(responce); // for parsing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        // update the address


        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        // get place

       String  getPlaceResponse =  given().log().all().queryParam("key", "qaclick123").
                queryParam("place_id",placeId).
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 =  ReUsableMethods.rawToJson(getPlaceResponse);

       String actualAddress = js1.getString("address");

        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);


    }








}
