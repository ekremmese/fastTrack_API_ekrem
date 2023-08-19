package com.cydeo.day02;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P06_JsonToJava  extends SpartanTestBase {

      /*
    Given accept type is application/json
    And Path param id = 10
    When i send GET request to /api/spartans
    Then status code is 200
    And content type is json
    And spartan data matching:
        id > 10
        name>Lorenza
        gender >Female
        phone >3312820936
    */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .body("id", is(10))
                .body("name", is("Lorenza"))
                .body("phone", is(3312820936L)).extract().response();

        //---------------First Approach -> response.path


        //Map<String, Object> spartanMap = response.as(Map.class);
        /*as() method does deserilization and to do so we Jackson or GSON libraries */

        Map<String,Object> spartanMap = response.path("");

        System.out.println(spartanMap);

        int id = (int) spartanMap.get("id");

        String  name =  (String) spartanMap.get("name");

        //-------------Second Approach ----------->
        JsonPath jsonPath = response.jsonPath();

        Map< String, Object > map = jsonPath.getMap("");

    }

    @Test
    public void getAllSpartans(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString()).extract().response();

        //First Approach -- path() method

        List<Map<String ,Object>> allSpartansList = response.path("");

        int id =  (int) allSpartansList.get(0).get("id");
        System.out.println("id = " + id);

        for (Map<String, Object> eachMap : allSpartansList) {
            System.out.println(eachMap);
        }


        //2n Approach jsonpath.body() method then we will retrieve with getList or getMap accordingly with data type
        JsonPath jsonPath = response.jsonPath();

        List<Map <String ,Object> > listOfAllSpartans = jsonPath.getList("");

        for (Map<String, Object> eachSpartan : listOfAllSpartans) {

            System.out.println("eachSpartan.get(\"name\") = " + eachSpartan.get("name"));

        }



    }


}
