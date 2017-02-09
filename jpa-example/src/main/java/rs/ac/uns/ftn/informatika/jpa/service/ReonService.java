package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;

public interface ReonService {
	
	public Reon createReon(Reon reon);
	
	public ArrayList<Reon> getReonsOfRestorans(Long rest);

}
