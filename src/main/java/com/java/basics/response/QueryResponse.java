package com.java.basics.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResponse
{
    private String age;
    private String deptLevel;
    private double salaryAmount;
}
