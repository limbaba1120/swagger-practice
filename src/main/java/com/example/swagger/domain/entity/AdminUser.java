package com.example.swagger.domain.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminUser {

    private String name;
    private LocalDateTime joinDate;
    private String ssn;

    public AdminUser(User user) {
        this.name = user.getName();
        this.joinDate = user.getJoinDate();
        this.ssn = user.getSsn();
    }
}
