package com.cydeo.day04;


import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P04_MockAPI extends SpartanTestBase {

    //https://7af34c41-1d07-48cc-99c7-671ffb53740a.mock.pstmn.io


    /*
    @BeforeAll
    public static void setup(){

        baseURI = "https://7af34c41-1d07-48cc-99c7-671ffb53740a.mock.pstmn.io";
        //basePath = "/spartans";

    }

     */

    @Test
    public void test1(){



        given().accept(ContentType.JSON)
                .when().get("/spartans").prettyPeek()
                .then().statusCode(200)

                .contentType(ContentType.JSON.toString())
                .body("id", everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AllSpartansSchema.json"))
                .header("Transfer-Encoding", notNullValue());


    }

}
