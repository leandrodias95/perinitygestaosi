package si.perinity.api.model.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import si.perinity.api.model.entity.Person;
import si.perinity.api.model.service.PersonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/perinitigestaosi/person")
public class PersonController {

	private final PersonService personService;

	@Operation(summary="Insert Person")
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public Person savePerson(@RequestBody @Valid Person person) {
		return personService.savePerson(person);
	}

	@Operation(summary="Update Person")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@PathVariable Long id, @RequestBody @Valid Person personUpdate) {
		personService.personUpdate(id, personUpdate);
	}
	
	@Operation(summary="Delete Person")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
	}

}
