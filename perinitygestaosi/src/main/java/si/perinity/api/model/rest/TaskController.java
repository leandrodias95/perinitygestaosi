package si.perinity.api.model.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import si.perinity.api.model.entity.Task;
import si.perinity.api.model.rest.dto.TaskDTO;
import si.perinity.api.model.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/perinitigestaosi/task")
public class TaskController {
	
	
	private final TaskService taskService;
	
	
	@Operation(summary="Insert Task")
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public Task save(@RequestBody @Valid TaskDTO dto) {
		return taskService.save(dto);
	}
	
	@Operation(summary="Alocate Person at task")
	@PutMapping("/tarefas/alocar/{taskId}")
	public Task allocatePersonToTask(@PathVariable Long taskId, @RequestParam Long personId) {
	    return taskService.allocatePersonToTask(taskId, personId);
	}
	
	@Operation(summary="Done Task")
	@PutMapping("/tarefas/finalizar/{taskId}")
	public Task taskDone(@PathVariable Long taskId) {
	    return taskService.doneTask(taskId);
	}
	
	@Operation(summary="List tree people without alocated")
	@GetMapping("/tarefas/pendentes")
	public List<Task> getOldestUnassignedTasks() {
	    return taskService.getOldestUnassignedTasks();
	}
}
