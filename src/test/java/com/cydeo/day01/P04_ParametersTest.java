package com.cydeo.day01;


import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class P04_ParametersTest extends SpartanTestBase {


    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 5
     * 3- When user sends GET request to /spartans/{id}
     * 5- Verify followings
     * - Status code should be 200
     * - Content Type is application/json
     * - ID is 5
     * - Name is "Blythe"
     * - gender is "Female"
     * - phone is 3677539542
     */

    @Test
    public void pathParam() {

        Response response = given().log().all().contentType(ContentType.JSON)
                .pathParam("id", 5).get("/spartans/{id}");

        response.prettyPrint();


        //   *5- Verify followings
        //     *     - Status code should be 200

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        //     *     - Content Type is application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());


        //     *     - ID is 5
        assertEquals(5, (Integer) response.path("id"));

        //     *     - Name is "Blythe"
        assertEquals("Blythe", response.path("name"));

        //     *     - gender is "Female"
        assertEquals("Female", response.path("gender"));

        //     *     - phone is 3677539542
        assertEquals(3677539542L, (Long) response.path("phone"));

    }

    /**
     * 1- Given accept type is Json
     * 2- Query Parameters values are
     * - gender —> Female
     * - nameContains —> e
     * 3- When user sends GET request to /spartans/search
     * 4- Print out Followings
     * - Total Element Number
     * - Get me first spartan name
     * - Get me second spartan id
     * - Get me last spartan name
     * - Get me all Spartan Names
     * <p>
     * 5- Verify followings
     * - Status code should be 200
     */


    @Test
    public void queryParam() {

        Response response = given().accept(ContentType.JSON).queryParams("gender", "Female", "nameContains", "e").get("/spartans/search");

        //response.prettyPrint();

        //*4- Print out Followings
        // Total Element Number

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        //Get me first spartan name
        System.out.println("response.path(\"content.name[0]\") = " + response.path("content.name[0]"));


        //Get me second spartan id
        System.out.println("response.path(\"content.id[1]\") = " + response.path("content.id[1]"));


        //Get me last spartan name
        System.out.println("response.path(\"content.name[-1]\") = " + response.path("content.name[-1]"));


        //     *     - Get me all Spartan Names
        System.out.println("response.path(\"content.name\") = " + response.path("content.name"));

        //     * 5- Verify followings
        //status code should be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());


    }


     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */

    @Test
    public void test() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("/spartans/{id}");


        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());


        assertEquals(ContentType.JSON.toString(), response.getContentType());

        assertEquals(response.path("error"), "Not Found");

    }


}
