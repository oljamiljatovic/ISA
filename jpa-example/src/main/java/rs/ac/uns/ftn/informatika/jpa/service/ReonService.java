package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;

public interface ReonService {
	
	public Reon createReon(Reon reon);
	
	public ArrayList<Reon> getReonsOfRestorans(Long rest);
	
	public void delete(Reon r);
	
	public ArrayList<Reon> findAll();
	
	public ArrayList<Reon> findByRestaurant(Long id);
	
	public void delete(Long id);
	
	public Reon findOne(Long id);
	
	public void update(Reon r);
}
