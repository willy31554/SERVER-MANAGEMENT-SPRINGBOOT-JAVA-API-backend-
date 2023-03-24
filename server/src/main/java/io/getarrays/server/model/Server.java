package io.getarrays.server.model;

import io.getarrays.server.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	@Id
	@GeneratedValue(strategy =AUTO)
	private Long id;
	@Column(unique = true)
	@NotEmpty(message = "Ip address cannot be empty or null")
	private String name;
	private String ipAddress;
	private String type;
	private String memory;
	private String imageUrl;
	private Status status;
}
