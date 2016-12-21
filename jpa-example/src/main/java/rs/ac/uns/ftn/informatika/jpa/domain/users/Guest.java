package rs.ac.uns.ftn.informatika.jpa.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guest")
public class Guest extends rs.ac.uns.ftn.informatika.jpa.domain.User{

	/*@Id
	@GeneratedValue
	private Long id;*/
	
/*	@Column(nullable = false)
	private String username;
	
	private String password;
	*/
	
	@Column(nullable = false)
	private String name;
	
	public Guest(){
		
	}




	
}
