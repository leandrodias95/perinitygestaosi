package si.perinity.api.model.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {

	@NotEmpty(message = "{field.title.required}")
	private String title;
	@NotEmpty(message = "{field.description.required}")
	private String description;
	@NotEmpty(message = "{field.deadline.requered}")
	private String deadline;
	private String department;
	@NotEmpty(message = "{field.duration.requered}")
	private String duration;
	private String done;
	private String idPerson;
}
