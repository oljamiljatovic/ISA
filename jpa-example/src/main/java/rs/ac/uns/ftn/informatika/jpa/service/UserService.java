package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserService {
	
	Page<User> findCities(String criteria, Pageable pageable);
	
	User getUser(String name, String username);
}
