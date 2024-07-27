package com.llp.amm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.amm.enums.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String username;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;
    @Enumerated(EnumType.STRING)
    @Column(name = "profile")
    private Profile profile;
}
