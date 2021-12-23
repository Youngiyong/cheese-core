package com.cheese.admin.model.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class Member {

    @NotBlank(message = "name은 필수 값입니다.")
    private String name;

    @Email
    @NotBlank(message = "email은 필수 값입니다.")
    private String email;

    @Min(value = 18, message = "18살 이상만 입장 가능합니다.")
    private int age;

    @NotNull(message = "금액은 필수 값 입니다.")
    @Min(value = 10000, message = "10000원 이상 소지해야합니다.")
    private Integer money;
    
    //getter,setter 생략
    
    
}