package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

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
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> update(
			@RequestBody Guest guest, @PathVariable Long id) throws Exception {
	
		Guest foundedGuest = guestService.findOne(id);
		foundedGuest.setName(guest.getName());
		foundedGuest.setSurname(guest.getSurname());
		Guest changedGuest = guestService.update(foundedGuest, id);
		
		
		return new ResponseEntity<Guest>(changedGuest, HttpStatus.OK);
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
}
