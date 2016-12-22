package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;

public interface ManagerRepository extends PagingAndSortingRepository<RestaurantManager, Long> {
	
	//public RestaurantManager save(RestaurantManager manag);
}
