package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P04_HamcrestMatcher extends HRTestBase {

       /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4
               ...
               ..
               .
    */

    @Test
    public void getAllRegions() {

        given().accept(ContentType.JSON)
                .when()
                .get("/regions")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items.region_name", containsInRelativeOrder("Europe" , "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1,2,3,4));


    }

    @Test
    public void test1(){

    }




}
