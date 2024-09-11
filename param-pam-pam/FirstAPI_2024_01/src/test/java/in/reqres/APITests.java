package in.reqres;

import data.People;
import data.PeopleCreated;
import data.ResourceDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static specification.Specification.*;

public class APITests {

    @Test
    public void firstTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("total", notNullValue())
                .body("total_pages", notNullValue())
                .body("data.id", not(hasItem(nullValue())))
                .body("data.first_name", hasItem("Tobias"))
                .statusCode(200);
    }

    @Test()
    public void prettyFirstTest() {
        ResourceDto resourse = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .extract().body().as(ResourceDto.class);
        resourse.getData().forEach(x-> System.out.println(x.getEmail()));
    }

    @Test
    public void secondTest(){
        Map<String,String> requestData = new HashMap<>();
        requestData.put("name","Alexey");
        requestData.put("job","QA-engineer");
        Response response = given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("name"),requestData.get("name"),
                "Ожидали создание пользователя с именем "+requestData.get("name")+", создался с именем: "+jsonResponse.get("name"));
        Assert.assertEquals(jsonResponse.get("job"),requestData.get("job"),
                "Ожидали создание пользователя с ролью "+requestData.get("job")+", создался с ролью: "+jsonResponse.get("job"));
    }

    @Test
    public void secondTest1() {
        People people = new People("Denis", "programmer");

        PeopleCreated peopleCreated = given()
                .contentType("application/json")
                .body(people)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().body().as(PeopleCreated.class);
        System.out.println("----------------------------------");
        System.out.println(peopleCreated.getCreatedAt());
    }


    @Test
    public void specTest() {
        People people = new People("Denis", "programmer");
        PeopleCreated peopleCreated = given()
                .spec(requestSpec())
                .body(people)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .spec(responseSpec201())
                .extract().body().as(PeopleCreated.class);
        System.out.println("__________");
        System.out.println(peopleCreated.getCreatedAt());
    }

    @Test()
    public void dtoSpecDefaultTest() {
        installSpec(requestSpec(), responseSpec201());

        People people = new People("Alexey", "QA-engineer");

        PeopleCreated peopleCreated = given()
                .body(people)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .extract().as(PeopleCreated.class);

        System.out.println(peopleCreated.getCreatedAt());

        deleteSpec();
    }
}
