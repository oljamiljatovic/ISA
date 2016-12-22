package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

   
   @Query("select r from User r where r.email = ?1 and r.password = ?2")
    User findUserByEmailAndPassword(String email, String password);
    
}
