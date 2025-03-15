package br.com.nata.services;

import br.com.nata.controllers.PersonController;
import br.com.nata.data.dto.v1.PersonDTO;
import br.com.nata.data.dto.v2.PersonDTOV2;
import br.com.nata.exception.ResourceNotFoundException;
import br.com.nata.mapper.custom.PersonMapper;
import br.com.nata.model.Person;
import br.com.nata.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import static br.com.nata.mapper.ObjectMapper.parseListObject;
import static br.com.nata.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;



    public List<PersonDTO> findAll(){
        var persons = parseListObject(repository.findAll(),PersonDTO.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }




    public PersonDTO findById(Long id){
        logger.info("Finding one Person! ");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity,PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }




  

    public PersonDTO create(PersonDTO person){

        logger.info("Creating one Person! ");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){

        logger.info("Creating one Person! ");

        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person){

        logger.info("Updating one Person! ");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one Person! ");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

    private  void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId()))
        .withSelfRel()
        .withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll())
        .withRel("findAll")
        .withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto))
        .withRel("create")
        .withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto))
        .withRel("update")
        .withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId()))
        .withRel( "delete")
        .withType("DELETE"));
        
        
    }

}
