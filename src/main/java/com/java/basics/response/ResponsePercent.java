package com.java.basics.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponsePercent
{
    private String deptName;
    private double empPercentage;
}
