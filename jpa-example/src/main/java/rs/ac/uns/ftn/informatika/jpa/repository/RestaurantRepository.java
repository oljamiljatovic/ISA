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
	
	@Override
	public Restaurant findOne(Long id);
	

	@Modifying
	@Query("update Restaurant set name = ?,type = ?,address =?, contact=? where id = ? ")
	public void updateRestaurant(String rest, String type, String address, String contact, Long id);
}
