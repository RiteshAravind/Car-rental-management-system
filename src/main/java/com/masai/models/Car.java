package com.masai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    private Integer id;
    private String make;
    private String makeYear;
    private String color;
    private String price;
    private String model;
    private Boolean available;
}
