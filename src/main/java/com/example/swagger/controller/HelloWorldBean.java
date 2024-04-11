package com.example.swagger.controller;

import lombok.Getter;

@Getter
public class HelloWorldBean {
    private String message;


    public HelloWorldBean(String message) {
        this.message = message;
    }
}
