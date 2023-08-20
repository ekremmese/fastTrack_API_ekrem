package com.cydeo.day03;

import com.cydeo.utility.SpartanCreater;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P01_Serialization extends SpartanTestBase {

    @Order(1)
    @Test
    public void postSpartan() {

        //verify the success message
        //extract id info from response

        Map<String, Object> spartanMap = new LinkedHashMap<>();

        spartanMap.put("name", "Barış");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1234567890);


        Response response = given().accept(ContentType.JSON)  // Hey Api I wanna get response as JSON
                .contentType(ContentType.JSON) //here it means hey API I am SENDING data in JSON format
                .body(spartanMap)   //serialization happens here to convert JSON
                .when().post("/spartans").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!")).extract().response();

        System.out.println("response.path(\"data.id\") = " + response.path("data.id"));


        //another one

    }


    @Order(2)
    @Test
    public void postSecondSpartan() {
        //verify the success message
        //extract id info from response

        Map<String, Object> spartanMap = new LinkedHashMap<>();

        spartanMap.put("name", "Barış");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1234567890);


        int idOfBaris = given().accept(ContentType.JSON)  // Hey Api I wanna get response as JSON
                .contentType(ContentType.JSON) //here it means hey API I am SENDING data in JSON format
                .body(spartanMap)   //serialization happens here to convert JSON
                .when().post("/spartans").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!")).extract().jsonPath().getInt("data.id");

        System.out.println("Barış id" + idOfBaris);

    }

@Test
public void spartanWithFaker(){

    given().accept(ContentType.JSON)  // Hey Api I wanna get response as JSON
            .contentType(ContentType.JSON) //here it means hey API I am SENDING data in JSON format
            .body(SpartanCreater.createSpartan())   //serialization happens here to convert JSON
            .when().post("/spartans").prettyPeek()
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .contentType(ContentType.JSON.toString())
            .body("success", is("A Spartan is Born!"));

}


    @Test
    public void putSpartan() {

        Map<String, Object> spartanMap = new LinkedHashMap<>();

        spartanMap.put("name", "from intellij");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1111111111);

        given().contentType(ContentType.JSON)
                .pathParam("id",124)
                .body(spartanMap)
                .when().put("/spartans/{id}").prettyPeek()
                .then().statusCode(HttpStatus.SC_NO_CONTENT);

        given().accept(ContentType.JSON)
                .pathParam("id",124)
                .when().get("/spartans/{id}").prettyPeek();



    }

    @Test
    public void patchSpartan() {

        Map<String, Object> spartanMap = new LinkedHashMap<>();

        spartanMap.put("name", "from intellij");
        spartanMap.put("gender", "Male");
        //spartanMap.put("phone", 1111111111);

        given().contentType(ContentType.JSON)
                .pathParam("id",4)
                .body(spartanMap)
                .when().patch("/spartans/{id}").prettyPeek()
                .then().statusCode(HttpStatus.SC_NO_CONTENT);

        given().accept(ContentType.JSON)
                .pathParam("id",4)
                .when().get("/spartans/{id}").prettyPeek();



    }

    @Test
    public void deleteSpartan(){

        given().pathParam("id", 3)
                .when().delete("/spartans/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);


    }



}
