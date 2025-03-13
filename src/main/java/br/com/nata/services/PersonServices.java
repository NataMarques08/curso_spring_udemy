package br.com.nata.services;

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


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;



    public List<PersonDTO> findAll(){
        return parseListObject(repository.findAll(),PersonDTO.class);
    }




    public PersonDTO findById(Long id){
        logger.info("Finding one Person! ");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return parseObject(entity,PersonDTO.class);
    }

    public PersonDTO createV2(PersonDTO person){

        logger.info("Creating one Person! ");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
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

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one Person! ");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

}
