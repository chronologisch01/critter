package com.udacity.jdnd.course3.critter.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
public class Pet implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private PetType type;

    private String name;

    @ManyToOne(targetEntity = Customer.class, optional=false)
    private Customer customer;



}
