package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
	//public Restaurant save(Restaurant rest);
}
