package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_ResponsePath extends HRTestBase {

    /*
        Given
                 accept type is application/json
         When
                 user sends get request to /regions/2
         Then
                 response status code must be 200
                 region_name is Americas
                 region_id is 2
                 print out all the links

      */
    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when()
                .get("/regions/{id}");

        // response status code must be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        //region_name is Americas
        assertEquals("Americas", response.path("region_name"));

        //region_id is 2
        assertEquals(2, (Integer) response.path("region_id"));

        // print out all the links
         List<String> allLinks = response.path("links.href");

        System.out.println("allLinks = " + allLinks);


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void parameterizedTest(int id, String regionName){

        System.out.println(id + "----" + regionName);

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/regions/{id}");

        // response status code must be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        //region_name is Americas
        assertEquals(regionName, response.path("region_name"));

        //region_id is id
        assertEquals(id, (Integer) response.path("region_id"));



    }




}
