package br.com.nata.controllers;


import br.com.nata.services.PersonServices;
import br.com.nata.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired // Injeta o service
    private PersonServices services;

    @RequestMapping(method = RequestMethod.GET, // Método GET
            produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public List<Person> findAll(){
        return services.findAll();
    }



    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, // Método GET
    produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public Person findById(@PathVariable("id") Long id){
       return services.findById(id);
    }

    @RequestMapping(
        method = RequestMethod.POST, // Método POST
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public Person create(@RequestBody Person person){
       return services.create(person);
    }

  @RequestMapping(
        method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE // Que produz uma aplicação com valor Json
    )
    public Person update(@RequestBody Person person){
       return services.create(person);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void delete(@PathVariable("id") Long id){
        services.delete(id);
    }


}
