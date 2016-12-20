package rs.ac.uns.ftn.informatika.jpa.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tomcat.jni.User;

@Entity
@Table(name="employee")
public class Employee extends rs.ac.uns.ftn.informatika.jpa.domain.User {

	
	
	@Column(nullable = false)
	private String name;
	
	public Employee(){
		super();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public Employee(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}*/
	
	
}
