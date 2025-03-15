package br.com.nata.controllers;


import br.com.nata.data.dto.v1.PersonDTO;
import br.com.nata.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person Endpoint",description = "Endpoint for managing people ")
public class PersonController {

    @Autowired // Injeta o service
    private PersonServices services;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @Operation(
        summary = "Find all people recorded in the database",
        tags = {"People"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @Content(mediaType = "application/xml")
                }
                
                ),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500",content = @Content),
        }, 
        description = "Find all people recorded in the database")
    public List<PersonDTO> findAll(){
        return services.findAll();
    }



    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @Operation(
        summary = "Finds a person by id",
        tags = {"People"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    
                }
                
                ),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500",content = @Content),
        }, 
        description = "Find a specific person")
    public PersonDTO findById(@PathVariable("id") Long id){
        var person = services.findById(id);
        person.setBirthDay(new Date());
        person.setPhoneNumber("+55(26)998733188");
        return person;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }


,
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }


 // Que produz uma aplicação com valor Json
    )
    public PersonDTO create(@RequestBody PersonDTO person){
        return services.create(person);
    }



  @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }


,
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }


 // Que produz uma aplicação com valor Json
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
