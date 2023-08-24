package com.cydeo.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;



public class TestApi {

    @Test
    public void test1(){


        RestAssured.baseURI = "http://localhost:8080";
        //RestAssured.basePath = "/profiles HTTP/1.1";

        given().accept(ContentType.JSON)
                .when().get("/profiles").prettyPeek();


    }


}
