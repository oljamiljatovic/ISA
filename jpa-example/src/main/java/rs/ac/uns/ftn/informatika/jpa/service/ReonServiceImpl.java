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
	public ArrayList<Reon> getReonsOfRestorans(Long rest) {
		return this.reonRepository.findByRestaurant(rest);
	}

	@Override
	public void delete(Reon r) {
		
		this.reonRepository.delete(r.getId());
	}

	@Override
	public ArrayList<Reon> findAll() {
		return this.findAll();
	}

	@Override
	public ArrayList<Reon> findByRestaurant(Long id) {
		return this.reonRepository.findByRestaurant(id);
	}

	@Override
	public void delete(Long id) {
		this.reonRepository.delete(id);
	}

	@Override
	public Reon findOne(Long id) {
		
		return this.reonRepository.findOne(id);
	}

	@Override
	public void update(Reon r) {
		this.reonRepository.updateReon(r.getName(), r.getLocation(), r.getNumberTable(), r.getId());
	}
	
	

}
