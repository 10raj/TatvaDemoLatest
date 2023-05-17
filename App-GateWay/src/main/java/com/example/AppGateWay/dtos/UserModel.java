package com.example.AppGateWay.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserModel {
    private String userName;
    private String password;
    private String name;
    private String type;
}