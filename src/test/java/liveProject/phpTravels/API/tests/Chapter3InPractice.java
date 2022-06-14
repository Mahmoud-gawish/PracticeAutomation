package liveProject.phpTravels.API.tests;

import com.tngtech.java.junit.dataprovider.*;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



@RunWith(DataProviderRunner.class)
public class Chapter3InPractice {
    private static RequestSpecification requestspec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createSpecBuilder(){
        requestspec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();

    }


    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                { "us", "90210", "Beverly Hills" },
                { "us", "12345", "Schenectady" },
                { "ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {

        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).spec(requestspec).
                when().
                get("/{countryCode}/{zipCode}").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @Test
    public void checkResponseCodeAndContentType(){

        given().
                spec(requestspec).
                when().
                get("/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void extractPlaceName(){

        String placeName =
                given().spec(requestspec).when()
                        .get("/us/90210").
                        then().extract()
                        .path("places[0].'place name'");
        System.out.println(placeName);
        Assert.assertEquals(placeName,"Beverly Hills");
    }





}
