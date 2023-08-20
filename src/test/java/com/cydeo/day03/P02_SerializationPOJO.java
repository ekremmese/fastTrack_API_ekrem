package com.cydeo.day03;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanCreater;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class P02_SerializationPOJO extends SpartanTestBase {

    @Test
    public void postSpartan(){

        Spartan spartan = new Spartan();

        spartan.setName("POST POJO");
        spartan.setGender("Male");
        spartan.setPhone(1231231231231L);

        System.out.println(spartan);


        given().log().body().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/spartans")
                .then().statusCode(201);


    }


}
