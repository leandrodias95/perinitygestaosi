package si.perinity.api.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500)
	@NotEmpty(message = "{field.title.required}")
	private String title;

	@Column(length = 500)
	@NotEmpty(message = "{field.description.required}")
	private String description;

	@Column
	@NotEmpty(message = "{field.deadline.requered}")
	private LocalDate deadline;

	@Column(length = 500)
	private String department;

	@Column
	@NotEmpty(message = "{field.duration.requered}")
	private Long duration;

	@Column(nullable = false)
	private boolean done;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
}
