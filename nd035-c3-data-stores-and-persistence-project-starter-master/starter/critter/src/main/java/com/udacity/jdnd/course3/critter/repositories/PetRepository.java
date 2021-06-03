package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> getAllByCustomerId(Long customerId);


}
