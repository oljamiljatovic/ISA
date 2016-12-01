package rs.ac.uns.ftn.informatika.jpa.service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface GuestService {

	ArrayList<Guest> getGuests();
	

}
