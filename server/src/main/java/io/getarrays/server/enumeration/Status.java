package io.getarrays.server.enumeration;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum Status {
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");
	private final String status;
	Status(String status){
		this.status=status;
	}
	public String getStatus(){
		return this.status;
	}
}
