package liveProject.phpTravels.API.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import liveProject.phpTravels.API.tests.files.Payload;
import liveProject.phpTravels.API.tests.files.ReUsableMethods;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;

import static  io.restassured.RestAssured.*;
public class RahulPractice {


  //given - all input details
    // when - submit the api - resource, http method
    // Then - validate the response
    // content of the file to string > content of file can convert into Byte > Byte data into String

    @Test
    public void test1() throws IOException {

        // add place then update place with new address >> get place validate if new address is present in the responce
        baseURI = "https://rahulshettyacademy.com";
      String responce =   given().log().all().
                queryParam("Key","qaclick123").
                header("Content-Type","application/json").
                body(new String(Files.readAllBytes(Paths.get("src/test/resources/TestData/location.json")))).when().post("maps/api/place/add/json").
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


    // Working with Dynamic JSon

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        baseURI = "https://rahulshettyacademy.com";
     String response =  given().header("Content-Type","application/json").
                body(Payload.AddBook(isbn,aisle)).
                post("/Library/Addbook.php").then().
                statusCode(200).extract().asString();

     JsonPath js =  ReUsableMethods.rawToJson(response);
     String id  = js.get("ID");
        System.out.println(id);

    }


    // use data provider
    @DataProvider(name="BooksData")
    public Object[][] getData(){

        // array = collection of elements
        // mutidimentional array == collection of arrays
       return new Object[][]{{"obfert","1234"},{"datayi","3957"}, {"sdscxz","0987"}};
    }

    // working with static Json payloads







}
