package com.cydeo.utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://35.168.8.49:8000";
        RestAssured.basePath = "/api";

    }


    @AfterAll
    public static void destroy() {

        RestAssured.reset();

    }

    public static RequestSpecification requestSpec(){

        RequestSpecification requestSpec = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        return  requestSpec;

    }

    public static ResponseSpecification responseSpec(){

        ResponseSpecification responseSpec = expect().contentType(ContentType.JSON)
                .statusCode(200);

        return  responseSpec;
    }


}
