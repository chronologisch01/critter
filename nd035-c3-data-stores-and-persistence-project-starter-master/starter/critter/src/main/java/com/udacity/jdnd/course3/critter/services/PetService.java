package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByCustomerId(long customerId){
        return petRepository.getAllByCustomerId(customerId);
    }

    public Pet getById(long petId){
        return petRepository.getOne(petId);
    }

    public Pet savePet(Pet pet, long ownerId){
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.inserPet(pet);
        customerRepository.save(customer);
        return pet;

    }

    public List<Pet> findPetsByOwner(long ownerId){
        // Owner = Customer of the pet
        List<Pet> pets = petRepository.getAllByCustomerId(ownerId);
        return pets;
    }

    public List<Pet> findAll(){
        List<Pet> pets = petRepository.findAll();
        return pets;
    }

}
