package com.java.basics.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse
{
    private int emp;
    private String refreshToken;
    private String name;
    private String password;
    private List<String> roles;
}
