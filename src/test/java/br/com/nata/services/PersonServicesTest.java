package br.com.nata.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nata.data.dto.v1.PersonDTO;
import br.com.nata.model.Person;
import br.com.nata.repository.PersonRepository;
import br.com.nata.unitetests.mapper.mocks.MockPerson;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service; 

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {

        Person person = input.mockEntity();
        Person persisted = person;
        persisted.setId(1L);
       
        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);
        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("api/person/v1/1")
        && link.getType().equals("GET") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("GET") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("POST") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("PUT") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("delete")
        && link.getHref().endsWith("api/person/v1/1")
        && link.getType().equals("DELETE") )
        
        ); 

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testCreateV2() {

    }

    @Test
    void testDelete() {

    }

  

    @Test
    void testFindById() {
        Person person = input.mockEntity();
        when(repository.findById(any())).thenReturn(Optional.of(person));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("api/person/v1/1")
        && link.getType().equals("GET") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("GET") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("POST") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("api/person/v1")
        && link.getType().equals("PUT") )
        
        ); 


        assertNotNull(result.getLinks().stream()
        .anyMatch(link -> link.getRel().value().equals("delete")
        && link.getHref().endsWith("api/person/v1/1")
        && link.getType().equals("DELETE") )
        
        ); 

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testUpdate() {

    }

    @Test
    void testFindAll() {

    }



}
