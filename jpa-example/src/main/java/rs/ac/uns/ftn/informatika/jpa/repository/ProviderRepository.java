package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;

public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {
	
	public Provider findByEmail(String email);
	
	@Modifying
	@Query("update Provider set name = ?, surname=?, contact=?, address=?, "
			+ "logFirstTime=?, password=? where id = ? ")
	public void updateManager(String name, String surname, String contact, String address,
			String logFirstTime, String password, Long id);

}
