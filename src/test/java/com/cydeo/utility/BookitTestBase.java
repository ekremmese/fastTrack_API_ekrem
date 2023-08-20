package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract  class BookitTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://api.qa.bookit.cydeo.com";
        //RestAssured.basePath = "/ords/hr";

    }


    @AfterAll
    public static void destroy() {

        RestAssured.reset();

    }

}
