package com.cydeo.day02;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P07_JsonToPOJO extends SpartanTestBase {

    @Test
    public void getSingleSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        //First Approach with response

        Spartan spartan = response.as(Spartan.class);

        System.out.println("spartan.getId() = " + spartan.getId());

        System.out.println("spartan.getGender() = " + spartan.getGender());


        System.out.println("spartan = " + spartan);

        //Second Approach with Jsonpath

        JsonPath jsonPath = response.jsonPath();

        Spartan spartan1 = jsonPath.getObject("", Spartan.class);

        System.out.println("spartan1.getGender() = " + spartan1.getGender());
        System.out.println("spartan1.getName() = " + spartan1.getName());


    }

    /*
        {
            "id": 10,
            "name": "Lorenza",
            "gender": "Female",
            "phone": 3312820936
        }
         */


    @Test
    public void searchSpartans(){

        Response response = given().accept(ContentType.JSON)
                .queryParams("nameContains", "f", "gender", "Female")
                .when().get("/spartans/search")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString()).extract().response();

        //Get me first spartan by using jsonpath



        JsonPath jsonPath = response.jsonPath();
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("spartan.getName() = " + spartan.getName());


    }

}
