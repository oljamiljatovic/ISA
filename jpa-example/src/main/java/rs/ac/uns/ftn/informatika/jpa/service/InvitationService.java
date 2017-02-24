package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface InvitationService {

	Invitation createNew(Invitation invitation);
	
	Invitation findInvitationBySenderAndRecipient(Long sender, Long friend);
	
	Invitation update(Invitation guest, Long id);

	List<Invitation> getRequests(Long id);
	
	
}
