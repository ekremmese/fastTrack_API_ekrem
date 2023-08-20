package com.cydeo.utility;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public  class SpartanCreater {

    public static Map<String, Object> createSpartan(){

        Faker faker = new Faker();

        Map<String, Object> spartanMap = new LinkedHashMap<>();

        spartanMap.put("name", faker.name().firstName());
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", faker.number().numberBetween(1000000000,9999999999L));

        return spartanMap;

    }




}
