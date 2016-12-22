package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;

public interface DrinkRepository extends Repository<Drink, Long> {
	
	@Query("select id,name,price FROM Drink")
	public ArrayList<Drink> findAll();
}
