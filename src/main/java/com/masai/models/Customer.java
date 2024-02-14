package com.masai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private int id;
    private String name;
    private String passcode;
    private String emailAddress;
}
