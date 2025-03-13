package br.com.nata.controllers;


import br.com.nata.data.dto.v1.PersonDTO;
import br.com.nata.data.dto.v2.PersonDTOV2;
import br.com.nata.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired // Injeta o service
    private PersonServices services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll(){
        return services.findAll();
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id){
       return services.findById(id);
    }

    @PostMapping(
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person){
       return services.createV2(person);
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
