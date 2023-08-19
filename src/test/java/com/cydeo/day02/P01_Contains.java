package com.cydeo.day02;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.impl.bootstrap.HttpServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P01_Contains extends HRTestBase {

     /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains Americas

     */

    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON).and().pathParam("region_id", 2)      // we can write anything here instead of region_id the only must is line 27 and line 28 must include same thing
                .when().get("regions/{region_id}");

        response.prettyPrint();

        // response status code must be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        // content type equals to application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        // response body contains Americas
        assertTrue(response.asString().contains("Americas"));


    }


}
