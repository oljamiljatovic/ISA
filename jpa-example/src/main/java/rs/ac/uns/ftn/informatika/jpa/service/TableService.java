package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;

public interface TableService {
	
	public Tablee createTable(Tablee table);
	
	public ArrayList<Tablee> findByReonAndRestaurant(Long reon, Long restaurant);

}
