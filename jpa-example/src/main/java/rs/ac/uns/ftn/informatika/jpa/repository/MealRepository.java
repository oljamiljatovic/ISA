package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;

public interface MealRepository extends PagingAndSortingRepository<Meal, Long>  {
	
	public ArrayList<Meal> findAll();
	void delete(Meal entity);
	public Meal findById(Long id);
	
	@Modifying
	@Query("update Meal set name = ?, description = ?,price = ?, restaurant = ? where id = ? ")
	public void updateMeal(String name, String desc, float price, Long rest, Long id);
	
	public ArrayList<Meal> findByRestaurant(Long restaurant);


}
