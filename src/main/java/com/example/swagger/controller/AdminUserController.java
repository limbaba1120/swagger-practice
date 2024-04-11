package com.example.swagger.controller;

import com.example.swagger.application.UserService;
import com.example.swagger.domain.entity.AdminUser;
import com.example.swagger.domain.entity.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;


//    @GetMapping("/users/{id}")
//    public ResponseEntity<MappingJacksonValue> retrieveUser4Admin(@PathVariable Long id) {
//        User user = userService.findById(id);
//
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
//
//        MappingJacksonValue mapping = new MappingJacksonValue(user);
//        mapping.setFilters(filters);
//
//        return ResponseEntity.ok()
//                .body(mapping);
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AdminUser> retrieveUser4Admin(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok()
                .body(new AdminUser(user));
    }
}
