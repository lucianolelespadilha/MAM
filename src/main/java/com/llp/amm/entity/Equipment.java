package com.llp.amm.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department department;

    @ManyToOne
    @JoinColumn(name="sector_id", nullable=false)
    private Sector  sector;

}
