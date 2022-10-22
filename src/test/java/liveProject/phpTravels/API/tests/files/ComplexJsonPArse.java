package liveProject.phpTravels.API.tests.files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonPArse {

    // when the API is still not ready you make mocking data to be ready
    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.CoursePrice());

        // print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // print purchaseAmount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // print title of the first course

        String title = js.getString("courses[0].title");
        System.out.println(title);


    }

}
