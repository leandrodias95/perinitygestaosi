package si.perinity.api.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import si.perinity.api.model.entity.Person;
import si.perinity.api.model.repository.PersonRepository;

@SpringBootTest
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testSavePerson() {
        Person person = new Person();
        person.setName("Leandro");
        
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        Person savedPerson = personService.savePerson(person);

        assertEquals("Leandro", savedPerson.getName());
    }

    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Leandro");

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        person.setName("Leandro Atualizado");
        personService.personUpdate(1L, person);

        assertEquals("Leandro Atualizado", person.getName());
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person();
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        personService.deletePerson(1L);

        Mockito.verify(personRepository, Mockito.times(1)).delete(person);
    }
}
