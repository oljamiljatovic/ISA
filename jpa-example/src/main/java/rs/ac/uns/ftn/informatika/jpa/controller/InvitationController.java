package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.InvitationService;

@Controller
@RequestMapping("/invitationController")
public class InvitationController {


	@Autowired
	private InvitationService invitationService;
	

	@RequestMapping(
			value = "/sendRequest/{id}/{idFriend}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation sendRequest(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		
		Invitation invitation = new Invitation(id,idFriend,"false");
		return invitationService.createNew(invitation);
		


	}
	
	
	@RequestMapping(
			value = "/cancelRequest/{id}/{idFriend}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation cancelRequest(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		
		Invitation foundInvitation = invitationService.findInvitationBySenderAndRecipient(id, idFriend);
		foundInvitation.setAccept("nevalidan");
		
		Invitation changeInvitation = invitationService.update(foundInvitation, foundInvitation.getId());
		
		 
		return changeInvitation;
		
		


	}
	
	
	@RequestMapping(
			value = "/getRequests/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody List<Invitation> getRequests(
			@PathVariable Long id) throws Exception {
	
		List<Invitation> list = invitationService.getRequests(id);
		System.out.println("Broj request"+ list.size());
		return list;
		


	}
	
	
	@RequestMapping(
			value = "/changeInvitation/{id}/{idFriend}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation changeInvitation(
			@PathVariable Long id, @PathVariable Long idFriend,  @RequestBody String accept) throws Exception {
	
		
		System.out.println("id olja"+ id);
		System.out.println("id vesna"+ idFriend);
		System.out.println("Unos"+ accept);
		
		Invitation foundInvitation = invitationService.findInvitationBySenderAndRecipient(id, idFriend);
		foundInvitation.setAccept(accept);
		
		Invitation changeInvitation = invitationService.update(foundInvitation, foundInvitation.getId());
		
		return changeInvitation;
		
		


	}
	
	
	
}
