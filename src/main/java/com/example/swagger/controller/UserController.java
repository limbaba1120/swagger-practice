package com.example.swagger.controller;

import com.example.swagger.application.UserService;
import com.example.swagger.domain.dto.request.AddUserRequest;
import com.example.swagger.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러입니다")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Validated @RequestBody AddUserRequest request) {
        User savedUser = userService.save(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

//        return ResponseEntity.created(location).build();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "사용자 정보 조회API", description = "사용자 ID를 이용해서 사용자 상세 정보를 조회.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok!!"),
            @ApiResponse(responseCode = "404", description = "user not found!!"),
            @ApiResponse(responseCode = "500", description = "internal server error!!"),
    })
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@Parameter(description="사용자 ID", required = true, example = "1") @PathVariable Long id) {
        User user = userService.findById(id);

        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users -> http://localhost:8088/users

        return entityModel;
//        return userService.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
