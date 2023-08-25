package com.cydeo.day04;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class P03_SpartanSpecTest extends SpartanAuthTestBase {

    @Test
    public void test1() {

        given().spec(requestSpec())
                .when().get("/spartans").prettyPeek()
                .then().spec(responseSpec());

    }


    @Test
    public void test2() {


        given().spec(requestSpec())
                .pathParam("id", 5)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec())
                .header("Date", Matchers.notNullValue());


    }


}
