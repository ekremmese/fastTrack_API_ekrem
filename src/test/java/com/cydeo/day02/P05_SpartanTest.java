package com.cydeo.day02;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P05_SpartanTest extends SpartanTestBase {

    /**
     * - Send a request to GET /spartans/search
     * - Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> f
     * - Log Everything
     * - Verify followings
     *       - Status Code is 200
     *       - ContentType is application/json
     *       - Total Element 4
     *       - jsonArray size hasSize 4
     *       - Names hasItem "Alfy"
     *       - Every gender is Female
     */

    @Test
    public void searchSpartans(){

        given().accept(ContentType.JSON)
                .and()
                .queryParams("gender", "Female", "nameContains","f")
                .log().all()
                .when().get("/spartans/search")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .body("totalElement", is(4))
                .body("content", hasSize(4))
                .body("content.name", hasItem("Alfy"))
                .body("content.gender", everyItem(is("Female")));



    }


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 128)
                .when().delete("/spartans/{id}").prettyPeek();
        //.then()
                //.statusCode(404);

        System.out.println("response.statusCode() = " + response.statusCode());

        JsonPath jsonPath = response.jsonPath();


        Assertions.assertEquals(404, response.statusCode());


    }


}
