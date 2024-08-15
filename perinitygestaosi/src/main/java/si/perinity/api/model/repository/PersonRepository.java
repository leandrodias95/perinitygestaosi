package si.perinity.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import si.perinity.api.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	Optional<Person> findByName(String name);

}
