package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import java.util.List;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Long>{

	
	public ArrayList<Guest> findAll();
	
	public Guest findGuestByEmail(String email);
	
	List<Guest> findByFriends(List friends);

	 @Query("select r from User r where r.email = ?1 and r.password = ?2")
	 User findUserByEmailAndPassword(String email, String password);
	  
	 @Query("select r from Guest r where r.name = ?1")
	 List<Guest> findGuestsByName(String name);
	 
	 @Query("select r from Guest r where r.surname = ?1")
	 List<Guest> findGuestsBySurname(String surname);
	 
	 
	 @Query("select r from Guest r where r.name = ?1 and r.surname = ?2")
	 List<Guest> findGuestsByNameAndSurname(String name, String surname);
	 
}
