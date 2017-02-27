package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.InvitationRepository;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Override
	public Invitation createNew(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

	@Override
	public Invitation findInvitationBySenderAndRecipient(Guest sender, Guest friend,String accept) {
		// TODO Auto-generated method stub
		return invitationRepository.findInvitationBySenderAndRecipient(sender, friend,accept);
	}

	@Override
	public Invitation update(Invitation invitation, Long id) {
		invitation.setId(id);
		return invitationRepository.save(invitation);
	}
	
	
	@Override
	public List<Invitation> getRequests(Guest guest){
		return invitationRepository.findRequests(guest);
	}
	
}
