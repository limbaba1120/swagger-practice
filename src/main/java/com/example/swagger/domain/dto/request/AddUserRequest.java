package com.example.swagger.domain.dto.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddUserRequest {
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다")
    private LocalDateTime joinDate;
    private String ssn;
}
