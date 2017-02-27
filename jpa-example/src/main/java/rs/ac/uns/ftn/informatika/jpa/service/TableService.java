package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;

public interface TableService {
	
	public Tablee createTable(Tablee table);
	
	public ArrayList<Tablee> findByReonAndRestaurant(Reon reon, Restaurant restaurant);
	
	public ArrayList<Tablee> findByRestaurant(Restaurant restaurant);
	
	public ArrayList<Tablee> findByReon(Reon id);

	public void delete(Long id);

	Tablee findById(Long id);
	
	public void updateTableFlag(Tablee table);

}
