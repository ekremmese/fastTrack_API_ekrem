package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {

    private int id;
    private String name;
    //private String description;
    private int capacity;
    //private boolean withTV;
    //private boolean withWhiteBoard;



}
