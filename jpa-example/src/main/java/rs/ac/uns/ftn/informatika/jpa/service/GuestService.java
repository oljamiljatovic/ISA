package rs.ac.uns.ftn.informatika.jpa.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface GuestService {

	ArrayList<Guest> getGuests();
	
	Guest findGuestByEmail(String email);
	
	Guest createNew(Guest guest);
	
	Guest update(Guest guest, Long id);

	Guest findOne(Long id);

	List<Guest> findByFriends(List friends);

	List<Guest> findGuestsByName(String name);

	List<Guest> findGuestsBySurname(String surname);

	List<Guest> findGuestsByNameAndSurname(String name, String surname);
	
	
}
