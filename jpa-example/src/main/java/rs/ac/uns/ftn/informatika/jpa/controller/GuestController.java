package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;


@Controller
@RequestMapping("/guestController")
public class GuestController {

	@Autowired
	private GuestService guestService;
	

	
	
	
	 @Autowired
	 private JavaMailSender mailSender;

	
	@GetMapping("/guests")
	@ResponseBody
	@Transactional(readOnly = true)
	public ArrayList<String> getGuests() {
	
		ArrayList<String> guestEmails= new ArrayList<String>();
		ArrayList<Guest> guests = this.guestService.getGuests();
		
		for(int i = 0 ; i <guests.size(); i++){
			guestEmails.add(guests.get(i).getEmail());
			
		}
		
		return guestEmails;
	}
	
	
	/*@RequestMapping(
			value = "/findGuestByEmail",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> findGuestByEmail(
			@RequestBody String email) throws Exception {
		
		Guest foundGuest = guestService.findGuestByEmail(email);
		System.out.println("guestController"+ foundGuest.getEmail()+"fas"+foundGuest.getName());
		
		return new ResponseEntity<Guest>(foundGuest, HttpStatus.OK);
	}
	*/

	@RequestMapping(
			value = "/regIn",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> regIn(
			@RequestBody Guest guest) throws Exception {
	
		
		Guest addedGuest = guestService.createNew(guest);
		sendMail(addedGuest);
		
		return new ResponseEntity<Guest>(addedGuest, HttpStatus.OK);
	}
	
	 public void sendMail(Guest guest) {
		 
		 SimpleMailMessage mail = new SimpleMailMessage();
		 mail.setTo(guest.getEmail());
		 mail.setFrom("oljicamiljatovic@gmail.com");
		 mail.setSubject("Info");
		 mail.setText("http://localhost:9999/guestController/accept/"+guest.getId());
		 
		   
	        try {
	        	   mailSender.send(mail);
	        } catch (MailException ex) {
	            System.out.println(ex);
	        }
	         
	
	    }

		@RequestMapping(
				value = "/getGuest",
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Guest> getGuest(
				/*@Valid*/ @RequestBody User user) throws Exception {
			
			String email = user.getEmail();
			
			Guest guest = guestService.findGuestByEmail(email);
			
			return new ResponseEntity<Guest>(guest, HttpStatus.OK);
		}
	 
  
		@RequestMapping(
				value = "/getFriends",
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Guest> getFriends(
				 @RequestBody User user) throws Exception {
			System.out.println("Pogodio za prijatelja");
			String email = user.getEmail();
			
			Guest guest = guestService.findGuestByEmail(email);
			
		ArrayList<Guest> newList = new ArrayList<Guest>();
			newList.add(guest);
			List<Guest> friends = guestService.findByFriends(newList);
			
			
			System.out.println("Broj u listi"+friends.size());
			return friends;
			/*return null;
			return new List<Guest>(guest, HttpStatus.OK);
*/		}
	 
		
		
		

	@RequestMapping(
			value = "/changeDeleteFriend/{id}/{idFriend}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody List<Guest> update(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		Guest foundedGuest = guestService.findOne(id);
		
		
		
		for(int i = 0; i< foundedGuest.getFriendOf().size() ; i++){
			if(foundedGuest.getFriendOf().get(i).getId().equals(idFriend)){
				
				foundedGuest.getFriendOf().remove(i);
			}
		}
		
		for(int i = 0; i< foundedGuest.getFriends().size() ; i++){
			if(foundedGuest.getFriends().get(i).getId().equals(idFriend)){
				foundedGuest.getFriends().remove(i);
			}
		}
		
	foundedGuest.setFriends(foundedGuest.getFriends());
		
		
		Guest changedGuest = guestService.update(foundedGuest, id);
		
		 
		return foundedGuest.getFriends();
	}
	

	@RequestMapping(
			value = "/accept/{id}",
			method = RequestMethod.GET)
	public void update(
			 @PathVariable Long id) throws Exception {
	
		System.out.println("Usao je u potvrdu");
		Guest foundedGuest = guestService.findOne(id);
		foundedGuest.setAccept("true");
		
		guestService.update(foundedGuest, id);
		/*foundedGuest.setName(guest.getName());
		foundedGuest.setSurname(guest.getSurname());
		Guest changedGuest = guestService.update(foundedGuest, id);
		*/
	
	
		
	}
	
	
	@RequestMapping(
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Guest> update(
			@RequestBody Guest guest, @PathVariable Long id) throws Exception {
	
		Guest foundedGuest = guestService.findOne(id);
		foundedGuest.setName(guest.getName());
		foundedGuest.setSurname(guest.getSurname());
		Guest changedGuest = guestService.update(foundedGuest, id);
		
		
		return new ResponseEntity<Guest>(changedGuest, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(
			value = "/search",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Guest> search(
			 @RequestBody String search) throws Exception {
		System.out.println("Pogodio za pretragu");
		
		System.out.println("Za pretragu "+ search);
		
		ArrayList<Guest> listOfGuests = new ArrayList<Guest>();
		
		if(search.contains(" ")){
		String[] array = search.split(" "); 
		System.out.println("Ime" + array[0]+" a prezime"  + array[1]);
		
		String name = array[0];
		String surname = array[1];
		
		listOfGuests = (ArrayList<Guest>) guestService.findGuestsByNameAndSurname(name, surname);
		
		}else{
			ArrayList<Guest> listBySurname= (ArrayList<Guest>) guestService.findGuestsBySurname(search);
			ArrayList<Guest> listByName= (ArrayList<Guest>) guestService.findGuestsByName(search);
			
			System.out.println("Broj pronadjenih po prezimenu"+ listBySurname.size());
			System.out.println("Broj pronadjenih po imenu"+ listByName.size());

			for(int i = 0; i<listByName.size(); i++){
				listOfGuests.add(listByName.get(i));
			}
			
			for(int j = 0; j<listBySurname.size() ; j++){
				listOfGuests.add(listBySurname.get(j));
			}
			
			
		}
		
	
		return listOfGuests;
		}
	
	
	@RequestMapping(
			value = "/changeAddFriend/{id}/{idFriend}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody List<Guest> addFriend(
			@PathVariable Long id, @PathVariable Long idFriend) throws Exception {
	
		Guest foundedGuest = guestService.findOne(id); //onom kom dodajem
		Guest guestToAdd = guestService.findOne(idFriend);
		
		foundedGuest.getFriendOf().add(guestToAdd);
		foundedGuest.getFriends().add(guestToAdd);
	/*	
		for(int i = 0; i< foundedGuest.getFriendOf().size() ; i++){
			if(foundedGuest.getFriendOf().get(i).getId().equals(idFriend)){
				
				foundedGuest.getFriendOf().remove(i);
			}
		}
		
		for(int i = 0; i< foundedGuest.getFriends().size() ; i++){
			if(foundedGuest.getFriends().get(i).getId().equals(idFriend)){
				foundedGuest.getFriends().remove(i);
			}
		}*/
		
	foundedGuest.setFriends(foundedGuest.getFriends());
		
		
		Guest changedGuest = guestService.update(foundedGuest, id);
		
		 
		return foundedGuest.getFriends();
	}
	
	
}
