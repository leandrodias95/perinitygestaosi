package si.perinity.api.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 

	@Column(length = 500)
	@NotEmpty(message = "{field.name.required}")
	private String name; 
	
	@Column(length = 500)
	private String department;
	
	 @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Task> tasks;
}
