package com.java.basics.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response
{
    private String message;
    private Object object;
}
