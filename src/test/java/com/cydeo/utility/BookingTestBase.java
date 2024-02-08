package com.cydeo.utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BookingTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }

/*
    public static String getToken(String username, String password){

        String token = given().accept(ContentType.JSON)
                .queryParams("username", username, "password", password)
                .when().get("https://restful-booker.herokuapp.com/auth")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");


        return  "Bearer " + token;

    }

 */


}
