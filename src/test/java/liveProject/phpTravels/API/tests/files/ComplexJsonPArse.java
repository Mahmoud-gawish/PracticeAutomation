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

        // print all  courses title and their respective prices
        for(int i =0 ; i< count ; i++ ){

           String titleIndex =  js.get("courses["+i+"].title");
           int priceIndex =  js.get("courses["+i+"].price");
            System.out.println("the title of " + i + " is : "+titleIndex);
            System.out.println("the price of " +i +" is : "+priceIndex);
        }

        // print number of copies cold by RPA Course
        System.out.println("print number of copies cold by RPA Course");

        for(int i =0 ; i< count ; i++ ){

            String courseTitles =  js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA")){
                int numberOfCopies =  js.get("courses["+i+"].copies");
                System.out.println(numberOfCopies);
                break;
            }


        }









    }

}
