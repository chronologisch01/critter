package com.udacity.jdnd.course3.critter.services;


import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomerByPetId(long petId){
        return petRepository.getOne(petId).getCustomer();
    }


    @Transactional
    public Customer saveCustomer(Customer customer, List<Long> petIds) throws Exception {
        customer.getPets().clear();

        for(Long petId: petIds){
            Pet pet = petRepository.findById(petId).orElseThrow(()-> new Exception("Pet with id "+ petId+ " not found"));
            customer.getPets().add(pet);
        }

        return customerRepository.save(customer);
    }

}
