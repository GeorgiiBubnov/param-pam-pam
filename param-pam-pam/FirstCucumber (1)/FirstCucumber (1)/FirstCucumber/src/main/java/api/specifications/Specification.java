package api.specifications;

import Config.Properties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(Properties.testProperties.reqresApiUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec201(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static void installSpec(RequestSpecification requestSpec){
        RestAssured.requestSpecification= requestSpec;
    }

    public static void installSpec(ResponseSpecification responseSpec){
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
