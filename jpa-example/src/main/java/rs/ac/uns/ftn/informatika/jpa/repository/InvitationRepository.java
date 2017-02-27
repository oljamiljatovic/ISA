package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long> {

	 @Query("select r from Invitation r where r.sender = ?1 and r.recipient = ?2 and r.accept = ?3 ")
	 Invitation findInvitationBySenderAndRecipient(Guest sender, Guest recipient,String accept);
	  
	 
	 @Query("select r from Invitation r where r.recipient = ?1")
	 List<Invitation> findRequests(Guest id);
	  
}
