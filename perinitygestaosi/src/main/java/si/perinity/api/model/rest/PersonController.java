package si.perinity.api.model.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Operation(summary="List name departament and hours")
	 @GetMapping("/pessoas")
	    public List<Map<String, Object>> getPersonTaskSummary() {
	        return personService.getPersonTaskSummary();
	    }
	
	@Operation(summary="Search people by name time and hours")
	@GetMapping("/pessoas/gastos")
	public double getAverageTaskDurationByPersonAndPeriod(
	        @RequestParam String name, 
	        @RequestParam LocalDate startDate, 
	        @RequestParam LocalDate endDate) {
	    return personService.getAverageTaskDurationByPersonAndPeriod(name, startDate, endDate);
	}
	
	@Operation(summary="List departaments people and tasks")
	@GetMapping("/departamentos")
	public List<Map<String, Object>> getDepartmentSummary() {
	    return personService.getDepartmentSummary();
	}

}
