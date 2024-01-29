package com.task_emirates;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task_Emirates {

    @Test
    public void test1(){

        Response response = RestAssured.get("http://api.zippopotam.us/us/90210");


        //System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("United States", response.path("country"));
        Assertions.assertEquals("-118.4065", response.path("places.longitude[0]"));
        Assertions.assertEquals("California", response.path("places.state[0]"));
        Assertions.assertEquals("34.0901", response.path("places.latitude[0]"));





    }




}
