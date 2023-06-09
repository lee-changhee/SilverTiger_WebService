package com.example.silvertiger.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class LoginDto {
    @NotNull
    @Size(min = 3, max =100)
    private String id;

    @NotNull
    @Size(min = 8, max = 300)
    private String password;
}
