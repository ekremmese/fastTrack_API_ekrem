package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class FormulaOneTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1";

    }

    @AfterAll
    public static void reset() {

        RestAssured.reset();

    }


}
