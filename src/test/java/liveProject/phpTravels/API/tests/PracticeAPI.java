package liveProject.phpTravels.API.tests;


import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class PracticeAPI {

    @Test
    public void requestUSZipCode90210(){

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }


    @Test
    public void repeatTest(){
        given().when().get("http://zippopotam.us/us/90210").
                then().assertThat().body("places[0].'state'",equalTo("California"));
    }

    @Test
    public void checkResponseContent(){
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void logRequestAndResponseDetails(){
        given().when().log().all().get("http://zippopotam.us/us/90210").
        then().log().body();
    }

    @Test
    public void checkResponseCode(){
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().statusCode(200);
    }

    @Test
    public void test_nubersOfPlaceNameInResponse(){
        given().when().
                get("http://zippopotam.us/us/90210").then().
                assertThat().body("places.'place name'",hasSize(1));
    }

}
