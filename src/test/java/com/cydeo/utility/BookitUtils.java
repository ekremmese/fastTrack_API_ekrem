package com.cydeo.utility;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class BookitUtils {

public static String getToken(String username, String password){

    String token = given().accept(ContentType.JSON)
            .queryParams("email", username, "password", password)
            .when().get("/sign")
            .then().statusCode(200)
            .extract().jsonPath().getString("accessToken");


    return  "Bearer " + token;

}



}
