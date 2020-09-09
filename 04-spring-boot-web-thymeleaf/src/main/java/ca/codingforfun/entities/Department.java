package ca.codingforfun.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Department {

	private Integer id;
	private String departmentName;

	public Department() {
	}
	
	public Department(int i, String string) {
		this.id = i;
		this.departmentName = string;
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
	
}
