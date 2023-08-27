package com.cydeo.day04;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;


public class P03_SpartanSpecTest extends SpartanAuthTestBase {

    @Test
    public void test1() {

        given().spec(requestSpec("admin","admin"))
                .when().get("/spartans").prettyPeek()
                .then().spec(responseSpec(200));

    }


    @Test
    public void test2() {


        given().spec(requestSpec("user","user"))
                .pathParam("id", 5)
                .when().delete("/spartans/{id}")
                .then().spec(responseSpec(403))
                .header("Date", Matchers.notNullValue());


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv",numLinesToSkip = 1)
        public void test3(String username, String password, int id, int statusCode){

        given().spec(requestSpec(username,password))
                .pathParam("id", id)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec(statusCode));



    }



}
