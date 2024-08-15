package si.perinity.api.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import si.perinity.api.model.entity.Person;
import si.perinity.api.model.entity.Task;
import si.perinity.api.model.repository.PersonRepository;
import si.perinity.api.model.repository.TaskRepository;
import si.perinity.api.model.rest.dto.TaskDTO;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository repository;
	private final PersonRepository personRepository;

	public Task save(TaskDTO dto) {

		Long duration = Long.parseLong(dto.getDuration());
		Boolean done = Boolean.parseBoolean(dto.getDone());
		if (dto.getIdPerson() == null || dto.getIdPerson().isEmpty() || dto.getIdPerson().equalsIgnoreCase("null")) {
				LocalDate deadline = LocalDate.parse(dto.getDeadline(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				Task task = new Task();
				task.setDepartment(dto.getDepartment());
				task.setDescription(dto.getDescription());
				task.setDeadline(deadline);
				task.setDuration(duration);
				task.setTitle(dto.getTitle());
				task.setDone(done);
				return repository.save(task);
			
		} else {
			Long idPerson = Long.parseLong(dto.getIdPerson());
			Optional<Person> optPerson = personRepository.findById(idPerson);
			if (optPerson.isPresent()) {
				Person person1 = optPerson.get();
				LocalDate deadline = LocalDate.parse(dto.getDeadline(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				Task task = new Task();
				task.setDepartment(dto.getDepartment());
				task.setDescription(dto.getDescription());
				task.setDeadline(deadline);
				task.setDuration(duration);
				task.setTitle(dto.getTitle());
				task.setDone(done);
				task.setPerson(person1);
				return repository.save(task);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
			}
		}
	}
	
	public Task allocatePersonToTask(Long taskId, Long personId) {
		Task task = repository.findById(personId)
				 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
	    Person person = personRepository.findById(personId)
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	    if (!task.getDepartment().equals(person.getDepartment())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa e Tarefa estão em departamentos diferentes");
	    }
	    task.setPerson(person);
	    return repository.save(task);
	}
	
	public Task doneTask(Long taskId) {
		Task taskMatch = repository.findById(taskId)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada")); 
		if(!(taskMatch.getPerson().getId()==null)|| !taskMatch.getPerson().getId().equals("null")) {
			taskMatch.setDone(true);
			return repository.save(taskMatch);
		}
		return taskMatch;
	}
	
	 public List<Task> getOldestUnassignedTasks() {
	        return repository.findAll().stream()
	                .filter(task -> task.getPerson() == null) 
	                .sorted((task1, task2) -> task1.getDeadline().compareTo(task2.getDeadline())) 
	                .limit(3)
	                .collect(Collectors.toList());
	    }
}
