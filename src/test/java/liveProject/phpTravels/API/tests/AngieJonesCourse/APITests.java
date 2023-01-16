package liveProject.phpTravels.API.tests.AngieJonesCourse;


import liveProject.phpTravels.API.tests.AngiJones.models.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class APITests {

    @Test
    public void getCategories(){
        String endPoint = "http://localhost:8888/api_testing/product/read.php";
        var response = given()
                .when()
                .get(endPoint).then();
        response.log().body();
    }

    @Test
    public void getProduct(){

        String endPoint = "http://localhost:8888/api_testing/product/read_one.php";
        var response  = given().
                queryParam("id",2).
                when().
                get(endPoint).
                then().assertThat().statusCode(200).
                body("id",equalTo("2")).
                body("name",equalTo("Cross-Back Training Tank")).
                body("description",equalTo("The most awesome phone of 2013!")).
                body("price",equalTo("299.00")).
                body("category_id",equalTo("2")).
                body("category_name",equalTo("Active Wear - Women"));
        response.log().body();


    }

    @Test
    public void getProducts(){

        String endPoint = "http://localhost:8888/api_testing/product/read.php";
        var response  = given().
                queryParam("id",2).
                when().
                get(endPoint).
                then().log().body().
                assertThat().statusCode(200).
                body("records.size()",greaterThan(2));



    }

    @Test
    public void createProduct(){
        String endPoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 12,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endPoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){

        String endPoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 19,
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 15,
                "category_id": 3
                }
                """;

        var response = given().body(body).when().put(endPoint).then();
        response.log().body();

    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 19
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endPoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue Water bottle , Hold 63 ounce",
                12,
                3
        );

        var response = given().body(product).when().post(endPoint).then();
        response.log().body();
    }


}
