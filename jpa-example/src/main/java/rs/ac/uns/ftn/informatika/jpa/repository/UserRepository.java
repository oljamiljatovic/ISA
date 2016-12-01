package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserRepository extends Repository<User, Long>  {

	public Page<User> findAll(Pageable pageable);
	
	
	public User findByNameAndUsernameAllIgnoringCase(String name, String username);
}
