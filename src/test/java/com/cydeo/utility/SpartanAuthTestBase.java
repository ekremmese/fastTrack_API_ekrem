package com.cydeo.utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.87.214.234:7000";
        RestAssured.basePath = "/api";

    }


    @AfterAll
    public static void destroy() {

        RestAssured.reset();

    }


    public static RequestSpecification requestSpec(String username,String password){

        RequestSpecification requestSpec = given().accept(ContentType.JSON)
                .auth().basic(username, password);

        return  requestSpec;

    }

    public static ResponseSpecification responseSpec(int statusCode){

        ResponseSpecification responseSpec = expect()//.contentType(ContentType.JSON)
                .statusCode(statusCode);

        return  responseSpec;
    }

}
