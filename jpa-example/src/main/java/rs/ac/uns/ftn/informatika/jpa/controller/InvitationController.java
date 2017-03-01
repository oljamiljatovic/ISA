package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Array;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;
import rs.ac.uns.ftn.informatika.jpa.service.InvitationService;

@Controller
@RequestMapping("/invitationController")
public class InvitationController {


	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(
			value = "/sendRequest/{id}/{idFriend}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation sendRequest(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		Guest sender = guestService.findOne(id);
		Guest friend = guestService.findOne(idFriend);
		Invitation invitation = new Invitation(sender,friend,"false");
		return invitationService.createNew(invitation);
		
		


	}
	
	
	@RequestMapping(
			value = "/cancelRequest/{id}/{idFriend}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation cancelRequest(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		
		Guest sender = guestService.findOne(id);
		Guest friend = guestService.findOne(idFriend);
		
		Invitation foundInvitation = invitationService.findInvitationBySenderAndRecipient(sender, friend,"false");
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
	
		Guest foundedGuest = guestService.findOne(id);
		List<Invitation> list = invitationService.getRequests(foundedGuest);
		List<Invitation> returnList = new ArrayList<Invitation>();
		
		for(int i =0; i<list.size(); i++){
			if(list.get(i).getAccept().equals("false")){
				returnList.add(list.get(i));
			}
		}
		System.out.println("Broj request"+ returnList.size());
		return returnList;
		
		//return new ResponseEntity<List,Guest>(changedGuest, HttpStatus.OK);


	}
	
	
	@RequestMapping(
			value = "/changeInvitation/{id}/{idFriend}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Invitation changeInvitation(
			@PathVariable Long id, @PathVariable Long idFriend,  @RequestBody String accept) throws Exception {
	
		
		Guest sender = guestService.findOne(id);
		Guest acceptor = guestService.findOne(idFriend);
		
		Invitation foundInvitation = invitationService.findInvitationBySenderAndRecipient(sender, acceptor,"false");
		foundInvitation.setAccept(accept);
		
		Invitation changeInvitation = invitationService.update(foundInvitation, foundInvitation.getId());
		
		return changeInvitation;
		
		


	}
	
	
	
}
