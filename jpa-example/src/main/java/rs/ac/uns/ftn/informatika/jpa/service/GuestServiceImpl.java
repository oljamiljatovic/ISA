package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public ArrayList<Guest> getGuests() {
		
		return this.guestRepository.findAll();
		//guestRepository.findOne(id);
	}

	@Override
	public Guest findGuestByEmail(String email) {
		return  guestRepository.findGuestByEmail(email);
	}

	@Override
	public Guest createNew(Guest guest) {
		return guestRepository.save(guest);
	}


	public Guest update(Guest guest, Long id) {
		guest.setId(id);
		return guestRepository.save(guest);
	}

	@Override
	public Guest findOne(Long id) {
		return guestRepository.findOne(id);
	}
	

	@Override
	public List<Guest> findByFriends(List friends) {
		return guestRepository.findByFriends(friends);
	}

	
	@Override
	public List<Guest> findGuestsByName(String name) {
		return guestRepository.findGuestsByName(name);
	}
	
	@Override
	public List<Guest> findGuestsBySurname(String surname) {
		return guestRepository.findGuestsBySurname(surname);
	}
	
	@Override
	public List<Guest> findGuestsByNameAndSurname(String name,String surname) {
		return guestRepository.findGuestsByNameAndSurname(name,surname);
	}
	

}
