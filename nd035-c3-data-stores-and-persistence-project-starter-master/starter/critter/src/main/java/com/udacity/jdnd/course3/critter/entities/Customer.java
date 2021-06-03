package com.udacity.jdnd.course3.critter.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String phoneNumber;

    @OneToMany(targetEntity = Pet.class)
    public List<Pet> pets;

    public void inserPet(Pet pet){
        pets.add(pet);
    }

}
