package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import java.lang.String;
import java.util.List;

public interface ManagerRepository extends PagingAndSortingRepository<RestaurantManager, Long> {
	
	//public RestaurantManager save(RestaurantManager manag);
	
	public RestaurantManager findByEmail(String email);

	@Modifying
	@Query("update RestaurantManager set name = ?, surname = ?,address =?, contact=?, password=? where id = ? ")
	public void updateManager(String name, String surname, String address, String contact,
			String password, Long id);
}
