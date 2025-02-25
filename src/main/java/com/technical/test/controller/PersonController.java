package com.technical.test.controller;

import java.util.List;

import javax.validation.Valid;

import com.technical.test.model.Person;
import com.technical.test.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController

@RequestMapping("/api/v1")
@CrossOrigin(origins = {"https://technicaltestclient.herokuapp.com","http://localhost:4200"})

public class PersonController {

  @Autowired

  private PersonRepository personRepository;


  @GetMapping("/users")

  public List<Person> getAllUsers() {

    return personRepository.findAll();

  }


  @GetMapping("/users/search/{searchItem}")

  public List<Person> getSearchUsers(@PathVariable(value = "searchItem") String lastName) {

    return personRepository.find(lastName);

  }

  @PostMapping("/users")

  public Person createUser(@Valid @RequestBody Person person) {

    return personRepository.save(person);

  }


}