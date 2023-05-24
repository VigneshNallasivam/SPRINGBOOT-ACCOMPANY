package com.java.basics.model;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_token_table")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String token;
    private Instant expiryDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp", referencedColumnName = "emp")
    private Employee employee;
}
