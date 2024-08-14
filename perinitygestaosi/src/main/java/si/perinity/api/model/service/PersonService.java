package si.perinity.api.model.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import si.perinity.api.model.entity.Person;
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
}
