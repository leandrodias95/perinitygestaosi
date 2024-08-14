package si.perinity.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import si.perinity.api.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
