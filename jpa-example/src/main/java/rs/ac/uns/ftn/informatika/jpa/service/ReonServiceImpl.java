package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.repository.ReonRepository;

@Service
@Transactional
public class ReonServiceImpl implements ReonService {
	
	@Autowired
	private ReonRepository reonRepository;

	@Override
	public Reon createReon(Reon reon) {
		return this.reonRepository.save(reon);
	}

	@Override
	public ArrayList<Reon> getReonsOfRestorans(String rest) {
		return this.reonRepository.findByRestaurant(rest);
	}
	
	

}
