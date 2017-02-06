package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
	//public Restaurant save(Restaurant rest);
	
	//@Query("select * FROM Restaurant")
	//public ArrayList<Restaurant> findAll();
	
	@Override
	public Iterable<Restaurant> findAll();
	
	public Restaurant findByName(String name);
	

	@Modifying
	@Query("update Restaurant set name = ?,type = ?,address =?, contact=?, drinks=?, meals=? where id = ? ")
	public void updateRestaurant(String rest, String name, String address, String contact, 
			ArrayList<String> drinks, ArrayList<String> meals, Long id);
}
