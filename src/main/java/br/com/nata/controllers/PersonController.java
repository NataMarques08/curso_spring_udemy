package br.com.nata.controllers;


import br.com.nata.data.dto.v1.PersonDTO;
import br.com.nata.data.dto.v2.PersonDTOV2;
import br.com.nata.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired // Injeta o service
    private PersonServices services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll(){
        return services.findAll();
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id){
        var person = services.findById(id);
        person.setBirthDay(new Date());
        person.setPhoneNumber("+55(26)998733188");
        return person;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public PersonDTO create(@RequestBody PersonDTO person){
        return services.create(person);
    }



  @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public PersonDTO update(@RequestBody PersonDTO person){
       return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }


}
