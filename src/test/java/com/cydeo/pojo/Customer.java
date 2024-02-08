package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Customer {
    private  String firstname;
    private  String lastname;
    private int totalprice;
    private boolean depositpaid;
    private  BookingDates bookingdates;

    private String additionalneeds;
}
