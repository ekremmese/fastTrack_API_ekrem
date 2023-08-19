package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class P03_JsonPath extends HRTestBase {

    /*
     Given
             accept type is application/json
     When
             user sends get request to /locations
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

    */

    @Test
    public void getLocations() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/locations");

        JsonPath jsonPath = response.jsonPath();

        //response status code must be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        // content type equals to application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //get the second city with JsonPath
        System.out.println("jsonPath.getString(\"items[1].city\") = " + jsonPath.getString("items[1].city"));

        //get the last city with JsonPath
        System.out.println("jsonPath.getString(\"items[1].city\") = " + jsonPath.getString("items[-1].city"));

        //get all country ids
        List<Integer> list = jsonPath.getList("items.country_id");
        System.out.println("list = " + list);


        //get all city where their country id is UK
        List<String> allCityUK = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println("allCityUK = " + allCityUK);


    }

     /*
    Given
             accept type is application/json
     When
             user sends get request to /employees
     Then
             response status code must be 200
             get me all employees first_name who is making salary more than 15000

    */

    @Test
    public void employees(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/employees");


        assertEquals(HttpStatus.SC_OK,response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();

        List<String > firstNames = jsonPath.getList("items.findAll {it.salary>15000}.first_name");
        System.out.println("firstNames = " + firstNames);


    }


}

























