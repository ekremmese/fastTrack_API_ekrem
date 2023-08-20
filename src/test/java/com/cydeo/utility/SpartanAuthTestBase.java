package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.87.214.234:7000";
        RestAssured.basePath = "/api";

    }


    @AfterAll
    public static void destroy() {

        RestAssured.reset();

    }

}
