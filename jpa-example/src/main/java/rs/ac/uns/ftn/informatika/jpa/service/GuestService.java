package rs.ac.uns.ftn.informatika.jpa.service;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
}
