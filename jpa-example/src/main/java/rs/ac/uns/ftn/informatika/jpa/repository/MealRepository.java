package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;

public interface MealRepository extends Repository<Meal, Long>  {
	
	@Query("select id,name,price FROM Meal")
	public ArrayList<Meal> findAll();

}
