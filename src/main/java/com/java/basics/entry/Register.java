package com.java.basics.entry;

import lombok.*;
import java.util.List;
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Register
{
    private String name;
    private String mail;
    private String password;
    private List<String> roles;
}
