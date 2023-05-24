package com.java.basics.model;

import com.java.basics.security.ERole;
import javax.persistence.*;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role;

    @Enumerated(EnumType.STRING)
    private ERole name;

}
