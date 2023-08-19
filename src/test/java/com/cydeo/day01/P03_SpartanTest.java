package com.cydeo.day01;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_SpartanTest  extends SpartanTestBase {

    /**
     * Send request to Spartan url and save the response
     * Accept application/json
     * GET /spartans
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - response
     *     - Content-Type
     *     - Status Code
     *     - Get me first spartan gender
     *     - Get me first spartan name
     *     - Get me all spartan name
     */


    @Test
    public void getAllSpartans() {


        Response response = given().log().all().accept(ContentType.JSON).get("/spartans");

        //response.prettyPrint();

        //print content type
        System.out.println("response.getContentType() = " + response.getContentType());

        //- Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());

        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());


        //     *     - Status Code
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        Assertions.assertEquals(HttpStatus.SC_OK,response.getStatusCode());


        //     *     - Get me first spartan gender
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));


        //     *     - Get me first spartan name
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

        //     *     - Get me all spartan name
        System.out.println("response.path(\"name\") = " + response.path("name"));


    }



}
