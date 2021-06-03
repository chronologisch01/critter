package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) throws Exception{
        if(petDTO.getId()>0L){
            Pet pet = new Pet();
            BeanUtils.copyProperties(petDTO, pet);

            Pet petReturn = petService.savePet(pet, petDTO.getOwnerId());


            BeanUtils.copyProperties(petReturn, petDTO);
            return petDTO;
        }
        return null;
    }

    @PostMapping("/{ownerId}")
    public PetDTO updatePet(@PathVariable(name="ownerId") Long ownerId, @RequestBody PetDTO petDTO) throws Exception {
        petDTO.setOwnerId(ownerId);
        return savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId){
        PetDTO petDTO = new PetDTO();
        Pet pet = petService.getById(petId);
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAll();
        return petToDTO(pets);

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId){
        List<Pet> pets = petService.findPetsByOwner(ownerId);
        return petToDTO(pets);
    }



    private List<PetDTO> petToDTO(List<Pet> pets){
        List<PetDTO> dtoList = new ArrayList<>();
        pets.forEach(pet-> {
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            dtoList.add(petDTO);
        });
        return dtoList;
    }



}
