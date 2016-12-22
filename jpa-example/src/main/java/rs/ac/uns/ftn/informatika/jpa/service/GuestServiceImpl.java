package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;

@Service
@Transactional
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
	

	

}
