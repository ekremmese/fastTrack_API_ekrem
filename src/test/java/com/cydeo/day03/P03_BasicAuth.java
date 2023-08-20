package com.cydeo.day03;

import com.cydeo.utility.SpartanAuthTestBase;
import com.cydeo.utility.SpartanCreater;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P03_BasicAuth extends SpartanAuthTestBase {


    /**
     * Role Based Access Control
     * <p>
     * Admin    GET     POST    PUT     PATCH       DELETE
     * Editor   GET     POST    PUT     PATCH       403
     * User     GET     403     403     403         403
     * Guest    401     401     401     401         401
     */

    @Test
    public void negativeTest() {


        given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .when().get("/spartans/{id}").prettyPeek()
                .then().statusCode(401)
                .body("error", is("Unauthorized"));


    }

    @Test
    public void getSpartanAsAUser() {

        given().log().all().accept(ContentType.JSON)
                .pathParam("id", 5)
                .auth().basic("user", "user")
                .when().get("/spartans/{id}").prettyPeek()
                .then().statusCode(200);

    }

    @Test
    public void deleteSpartanAsEditor() {

        given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .auth().basic("editor", "editor")
                .when().delete("/spartans/{id}")
                .then().statusCode(403)
                .body("error", is("Forbidden"));

    }

    /**
     * Create one DDT Test to do RBAC
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/GETSpartans.csv",numLinesToSkip = 0)
    public void getSpartans(String username, String password, int statusCode) {
        given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .auth().basic(username, password)
                .when().get("/spartans/{id}")
                .then().statusCode(statusCode);


    }


    @ParameterizedTest
    @CsvFileSource(resources = "/GETSpartans.csv", numLinesToSkip = 4)
    public void deleteSpartans(String username, String password, int statusCode){

        int id = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(SpartanCreater.createSpartan())
                .auth().basic("admin", "admin")

                .when().post("/spartans")
                .then().statusCode(201).extract().jsonPath().getInt("data.id");


        given().pathParam("id",id)
                .auth().basic(username,password)
                .when().delete("/spartans/{id}")
                .then().statusCode(statusCode);

        List<Integer> list = given().auth().basic("admin", "admin")
                .when().get("/spartans")
                .then().statusCode(200).extract().jsonPath().getList("id");

        System.out.println(list.size());


    }



}
