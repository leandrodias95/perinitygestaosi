package si.perinity.api.model.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import si.perinity.api.model.entity.Person;
import si.perinity.api.model.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void testSavePerson() throws Exception {
        Person person = new Person();
        person.setName("John Doe");

        // Simulando o comportamento do service
        when(personService.savePerson(any(Person.class))).thenReturn(person);

        // Executando a requisição de teste e verificando o status esperado
        mockMvc.perform(post("/api/perinitigestaosi/person/insert")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"John Doe\"}"))
            .andExpect(status().isCreated());
    }
}

