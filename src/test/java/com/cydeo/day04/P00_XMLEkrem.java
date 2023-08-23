package com.cydeo.day04;

import com.cydeo.utility.SpartanAuthTestBase;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P00_XMLEkrem extends SpartanAuthTestBase {

    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/spartans").prettyPeek();

        XmlPath xmlPath = response.xmlPath();


        //get first Spartan name
        System.out.println(xmlPath.getString("List.item[0].name"));


    }




}
