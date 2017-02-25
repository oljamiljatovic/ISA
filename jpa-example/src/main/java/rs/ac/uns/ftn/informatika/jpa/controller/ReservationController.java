package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservedTablesService;
import rs.ac.uns.ftn.informatika.jpa.service.TableService;

@Controller
@RequestMapping("/reservationController")
public class ReservationController {

	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservedTablesService reservedTablesService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private JavaMailSender mailSender;
	

	@RequestMapping(
			value = "/addReservation/{idStola}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> addReservation(
	@RequestBody Reservation reservation ,@PathVariable Long idStola)  throws Exception {
		
		Tablee foundTable = tableService.findById(idStola);
		Reservation newReservation = null;
		
		
		Long idChange = (long) -1;
		Reservation neww = null;
		if((neww = reservationService.findReservationByAll(reservation.getIdGuest(),reservation.getIdRestaurant(),reservation.getDate(),reservation.getTime())) == null){
			
			List<Tablee> res = reservation.getReservedTables();
			res.add(foundTable);
			reservation.setReservedTables(res);
			
			 newReservation = reservationService.createNew(reservation);
		}else {
		
			List<Tablee> res = neww.getReservedTables();
			res.add(foundTable);
			neww.setReservedTables(res);
			newReservation = reservationService.update(neww,neww.getId());
			
		}
		

		return new ResponseEntity<Reservation>( newReservation, HttpStatus.OK);
	
	}
	
	
	

	@RequestMapping(
			value = "/getFriends/{idRezervacije}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Guest>> getFriends(
			@PathVariable Long idRezervacije)  throws Exception {
		
		Reservation foundReservation = reservationService.findOne(idRezervacije);
		Long senderId = foundReservation.getIdGuest();
		
		Guest sender = guestService.findOne(senderId);
		
		ArrayList<Guest> newList = new ArrayList<Guest>();
		newList.add(sender);
		List<Guest> friendsOfGuest = guestService.findByFriends(newList);
		
		System.out.println("Broj prijatelja koji vraca"+ friendsOfGuest.size());
		return new ResponseEntity<List<Guest>>( friendsOfGuest, HttpStatus.OK);
	
	}
	
	

	@RequestMapping(
			value = "/sendRequestByMail/{idPozvanogPrijatelja}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Guest>> sendRequestByMail(
			@RequestBody Long  idRezervacije, @PathVariable Long idPozvanogPrijatelja)  throws Exception {
		System.out.println("Usao  u prije send");
		Guest guestToSend = guestService.findOne(idPozvanogPrijatelja);
		Reservation reservationToSend = reservationService.findOne(idRezervacije);
		
		sendMail(guestToSend,reservationToSend);
		
		//return new ResponseEntity<List<Guest>>( friendsOfGuest, HttpStatus.OK);
	return null;
	}
 public void sendMail(Guest guest, Reservation reservation) {
		 System.out.println("Usao u send");
		 SimpleMailMessage mail = new SimpleMailMessage();
		 System.out.println("Saljem na"+ guest.getEmail());
		 mail.setTo(guest.getEmail());
		 mail.setFrom("oljicamiljatovic@gmail.com");
		 mail.setSubject("Request");
		 //mail.setText("http://localhost:9999/guestController/accept/"+guest.getId());
		 mail.setText("http://localhost:9999/requestForDinner.html/"+guest.getId()+"/"+reservation.getId());
		   
	        try {
	        	   mailSender.send(mail);
	        	   System.out.println("Poslaooooo");
	        } catch (MailException ex) {
	            System.out.println(ex);
	        }
	         
	
	    }
	@RequestMapping(
			value = "/getReservations",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reservation>> getReservations() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		reservations = this.reservationService.findByIdGuest(u.getId());
		return new ResponseEntity<ArrayList<Reservation>>(reservations, HttpStatus.OK);
	}
		
		
	
}
