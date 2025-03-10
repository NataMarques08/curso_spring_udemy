package br.com.nata.services;

import br.com.nata.exception.ResourceNotFoundException;
import br.com.nata.model.Person;
import br.com.nata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;



    public List<Person> findAll(){
        return repository.findAll();
    }




    public Person findById(Long id){
        logger.info("Finding one Person! ");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

    }

    public Person create(Person person){

        logger.info("Creating one Person! ");


        return repository.save(person);
    }

    public Person update(Person person){

        logger.info("Updating one Person! ");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting one Person! ");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

}
