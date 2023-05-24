package com.java.basics.entry;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Login
{
    private String name;
    private String password;

}