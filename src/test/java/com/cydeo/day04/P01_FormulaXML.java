package com.cydeo.day04;


import com.cydeo.utility.FormulaOneTestBase;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class P01_FormulaXML extends FormulaOneTestBase {

    @Test
    public void xmlTest() {

        Response response = given().get("/drivers")
                //.prettyPeek()
                .then().statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath();

        //first Driver Given name

        // path

        String firstDriverName = xmlPath.getString("MrData.DriverTable.Driver[0].GivenName");
        System.out.println(firstDriverName);

        //all drivers given name

        List<String> listOfGivenName = xmlPath.getList("MrData.DriverTable.Driver.GivenName");
        System.out.println(listOfGivenName);
        System.out.println(listOfGivenName.size());

        // first driver driverID attribute
        String firstDriverID = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId");
        System.out.println(firstDriverID);

        //print all drivers driver Id attribute
        List<String> listOfAllDriverIdAttribute = xmlPath.getList("MRData.DriverTable.Driver.@driverId");
        System.out.println("listOfAllDriverIdAttribute = " + listOfAllDriverIdAttribute);

        //find all driver given names if their nationality is Italian
        // path ---> MRData.DriverTable.Driver.findAll  (it.Nationality=='Italian').GivenName
        List<String> italianDrivers = xmlPath.getList("MRData.DriverTable.Driver.findAll  {it.Nationality=='Italian'}.GivenName");
        System.out.println(italianDrivers);
        System.out.println(italianDrivers.size());

        //find all driver family names who are german
        List<String> list = xmlPath.getList("MRData.DriverTable.Driver.findAll {it.Nationality=='German'}.GivenName");
        System.out.println(list);

        //find all driver given names who born after

    }


}
