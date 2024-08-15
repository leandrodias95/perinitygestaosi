package si.perinity.api.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import si.perinity.api.model.entity.Person;
import si.perinity.api.model.entity.Task;
import si.perinity.api.model.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository repository;

	public Person savePerson(Person person) {
		return repository.save(person);
	}

	public void personUpdate(Long id, Person personUpdate) {
		repository.findById(id).map(person -> {
			person.setName(personUpdate.getName());
			repository.save(person);
			return person;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found!"));
	}

	public void deletePerson(Long id) {
		repository.findById(id).map(person -> {
			repository.delete(person);
			return person;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found!"));
	}
	
	public List<Map<String, Object>> getPersonTaskSummary() {
        List<Person> people = repository.findAll();
        
        return people.stream().map(person -> {
            Long totalHours = person.getTasks().stream()
                .mapToLong(task -> task.getDuration() != null ? task.getDuration() : 0)
                .sum();

            Map<String, Object> personSummary = new HashMap<>();
            personSummary.put("name", person.getName());
            personSummary.put("department", person.getDepartment());
            personSummary.put("totalHours", totalHours);
            
            return personSummary;
        }).collect(Collectors.toList());
    }
	
	public double getAverageTaskDurationByPersonAndPeriod(String name, LocalDate startDate, LocalDate endDate) {
        Person person = repository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        List<Task> tasksInPeriod = person.getTasks().stream()
                .filter(task -> task.getDeadline().isAfter(startDate) && task.getDeadline().isBefore(endDate))
                .collect(Collectors.toList());
        return tasksInPeriod.stream()
                .mapToLong(Task::getDuration)
                .average()
                .orElse(0); 
    }
	
    public List<Map<String, Object>> getDepartmentSummary() {
        return repository.findAll().stream()
            .collect(Collectors.groupingBy(Person::getDepartment))  
            .entrySet().stream()
            .map(entry -> {
                String department = entry.getKey();
                List<Person> peopleInDepartment = entry.getValue();
                long personCount = peopleInDepartment.size();
                long taskCount = peopleInDepartment.stream()
                    .flatMap(person -> person.getTasks().stream())
                    .count();
                Map<String, Object> summary = Map.of(
                    "department", department,
                    "personCount", personCount,
                    "taskCount", taskCount
                );
                
                return summary;
            })
            .collect(Collectors.toList());
    }
}


