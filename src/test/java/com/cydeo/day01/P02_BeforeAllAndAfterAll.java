package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_BeforeAllAndAfterAll {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.87.214.234:1000";
        RestAssured.basePath = "/ords/hr";

    }


    @AfterAll
    public static void destroy(){

        RestAssured.reset();

    }

    @Test
    public void simpleGetRequest(){

        /**
         * 1. Send request to HR url and save the response
         * 2. GET /regions
         * 3. Store the response in Response Object that comes from GET Request
         * 4. Print out followings
         *     - Headers
         *     - Content-Type
         *     - Status Code
         *     - Response
         *     - Date
         *     - Verify response body has "Europe"
         *     - Verify response has Date
         */

        Response response = get("/regions");

        //print with pretty peek
        //response.prettyPeek();   // prints response header too and returns Response so that we can chain the method

        //print response
        //response.prettyPrint();

        //print out headers
        System.out.println("response.headers() = " + response.headers());

        //System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        //System.out.println(response.getHeaders());

        //Content Type
        System.out.println("response.getContentType() = " + response.getContentType());

        System.out.println("response.getHeader(\"Content-Type=\") = " + response.getHeader("Content-Type"));

        //statusCode
        System.out.println("response.getContentType() = " + response.getContentType());

        //Date
        System.out.println(response.header("Date"));

        //verify response has Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //verify response body has Europe
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe")); // --->> this is the 1st method that Jamal thought


    }


    @DisplayName("GET/employees/100")
    @Test
    public void getOneEmployee(){

        Response response = get("http://54.87.214.234:1000/ords/hr/employees/100");

        //System.out.println("response.prettyPeek() = " + response.prettyPeek());

        //print first name
        System.out.println("response.path(\"first_name\") = " + response.path("first_name"));

        //print last name
        System.out.println("response.path(\"last_name\") = " + response.path("last_name"));

        //verify status code is 200
        assertEquals(200,response.getStatusCode());

        assertEquals(HttpStatus.SC_OK,response.getStatusCode());

        //verify fits name is steven
        assertEquals("Steven", response.path("first_name"));

        //Verify content-Type is application/json

        assertEquals(ContentType.JSON.toString(),response.contentType());






    }


}
