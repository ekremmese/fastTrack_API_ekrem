package com.cydeo.day03;

import com.cydeo.pojo.Campuses;
import com.cydeo.pojo.Cluster;
import com.cydeo.utility.BookitTestBase;
import com.cydeo.utility.BookitUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P04_Bookit extends BookitTestBase {


    /**
     *
     *     send a request to get all campuses
     *     verify status code 200
     *     Create an POJO to get all data
     *        Find out how many campus we have
     *        Find out how many cluster we have in VA
     *        Find out how many room  we have in light-side
     */

    @Test
    public void getCampuses(){

        String email = "blyst6@si.edu";
        String password = "barbabaslyst";


        String token = BookitUtils.getToken(email,password);

        //send a request to get all campuses and verify status code 200
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses")
                .then().statusCode(200).extract().jsonPath();


        List<Campuses> campusesList = jsonPath.getList("", Campuses.class);
        //System.out.println(campusesList);

        //Find out how many campus we have

        System.out.println("campusesList.size() = " + campusesList.size());

        //Find out how many cluster we have in VA
        System.out.println("campusesList.get(0).getClusterList().size() = " + campusesList.get(0).getClusterList().size());

        //Find out how many room  we have in light-side
        System.out.println("campusesList.get(0).getClusterList().get(0).getRoomList().size() = " + campusesList.get(0).getClusterList().get(0).getRoomList().size());

        //find me all rooms where capacity is 6
        List<Object> list = jsonPath.getList("clusters.rooms findAll {capacity=6}.rooms");
        System.out.println(list.size());


    }

}
