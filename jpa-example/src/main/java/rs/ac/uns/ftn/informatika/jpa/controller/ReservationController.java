package rs.ac.uns.ftn.informatika.jpa.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
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
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AssignReonService assignReonService;
	

	@RequestMapping(
			value = "/addReservation/{idStola}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> addReservation(
	@RequestBody Reservation reservation ,@PathVariable Long idStola)  throws Exception {
		
		Tablee foundTable = tableService.findById(idStola);
		System.out.println("nasao table"+ foundTable.getId());
		Reservation newReservation = null;

		
		Long idChange = (long) -1;
		Reservation neww = null;
		
		
		System.out.println("REZERVACIJA"+ reservation.getIdGuest().getId() + " rest"+ reservation.getIdRestaurant().getId());
		
		if(reservationService.findReservationByAll(reservation.getIdGuest(),reservation.getIdRestaurant(),reservation.getDate(),reservation.getTime()) == null){
			
			if(reservation.getReservedTables() == null){
				
				List<Tablee> pomocna = new ArrayList<Tablee>();
				pomocna.add(foundTable);
				reservation.setReservedTables(pomocna);
			
			}else{
				
				List<Tablee> res = reservation.getReservedTables();
				res.add(foundTable);
				reservation.setReservedTables(res);
			}
			 newReservation = reservationService.createNew(reservation);
			
			 for(int i  = 0; i<newReservation.getReservedTables().size();i++){
					
					ReservedTables newReservedTable = new ReservedTables(newReservation.getIdRestaurant(),newReservation.getReservedTables().get(i),newReservation.getDate(),newReservation.getTime(),newReservation.getDuration());
							
					reservedTablesService.createNew(newReservedTable);
				}
			
			 
		}else {
			
			neww = reservationService.findReservationByAll(reservation.getIdGuest(),reservation.getIdRestaurant(),reservation.getDate(),reservation.getTime()); 
			
			List<Tablee> res = neww.getReservedTables();
			res.add(foundTable);
			neww.setReservedTables(res);
			newReservation = reservationService.update(neww,neww.getId());
			
			
			ReservedTables newReservedTable = new ReservedTables(newReservation.getIdRestaurant(),foundTable,newReservation.getDate(),newReservation.getTime(),newReservation.getDuration());
			
			reservedTablesService.createNew(newReservedTable);
			
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
		Long senderId = foundReservation.getIdGuest().getId();
		
		Guest sender = guestService.findOne(senderId);
		
		ArrayList<Guest> newList = new ArrayList<Guest>();
		newList.add(sender);
		List<Guest> friendsOfGuest = guestService.findByFriends(newList);
		
		return new ResponseEntity<List<Guest>>( friendsOfGuest, HttpStatus.OK);
	
	}
	
	

	@RequestMapping(
			value = "/sendRequestByMail/{idPozvanogPrijatelja}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Guest>> sendRequestByMail(
			@RequestBody Long  idRezervacije, @PathVariable Long idPozvanogPrijatelja)  throws Exception {
		
		Guest guestToSend = guestService.findOne(idPozvanogPrijatelja);
		Reservation reservationToSend = reservationService.findOne(idRezervacije);
		
		sendMail(guestToSend,reservationToSend);
		
		//return new ResponseEntity<List<Guest>>( friendsOfGuest, HttpStatus.OK);
	return null;
	}
 public void sendMail(Guest guest, Reservation reservation) {
	
		 SimpleMailMessage mail = new SimpleMailMessage();
	
		 mail.setTo(guest.getEmail());
		 mail.setFrom("oljicamiljatovic@gmail.com");
		 mail.setSubject("Request");
		 mail.setText("http://localhost:9999/requestForDinner.html?idGuest="+guest.getId()+"&idReservation="+reservation.getId());
		   
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
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<Reservation> resFromAccept = new ArrayList<Reservation>();
		Guest guest = guestService.findOne(u.getId());
		System.out.println(guest.getName());
		reservations = this.reservationService.findByIdGuest(guest);
		System.out.println(reservations.size());
		resFromAccept = this.reservationService.findByAcceptedFriends_Id(u.getId());
		if(reservations.isEmpty()){
			reservations = resFromAccept;
		}else{
			if(!resFromAccept.isEmpty()){
				//System.out.println("Ima Accepted ");
				reservations.addAll(resFromAccept);
			}
		}
		/*if(!resFromAccept.isEmpty())
			System.out.println("Accepted "+resFromAccept.get(0).getId());*/
		return new ResponseEntity<ArrayList<Reservation>>(reservations, HttpStatus.OK);
	}
		
		
	
 @RequestMapping(
			value = "/getReservationById/{idReservation}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> getReservationById(
			@PathVariable Long idReservation)  throws Exception {
	
		Reservation reservation = reservationService.findOne(idReservation);
		
		return new ResponseEntity<Reservation>( reservation, HttpStatus.OK);
	
	}
 
 
 
 @RequestMapping(
			value = "/getSenderById/{idSender}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getSenderById(
			@PathVariable Long idSender)  throws Exception {
		
	 	Guest sender = guestService.findOne(idSender);
		return new ResponseEntity<Guest>( sender, HttpStatus.OK);
	
	}
 
 @RequestMapping(
			value = "/addFriendToReservation/{idGuest}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> addFriendToReservation(
	@RequestBody Long idReservation ,@PathVariable Long idGuest)  throws Exception {
		
	 	Guest friendToAdd = guestService.findOne(idGuest);
	 	Reservation reservation  = reservationService.findOne(idReservation);
	 
	 	List<Guest> friends = reservation.getAcceptedFriends();
		friends.add(friendToAdd);
		reservation.setAcceptedFriends(friends);;
		Reservation updatedReservation = reservationService.update(reservation,reservation.getId());
	 	
		return new ResponseEntity<Reservation>( updatedReservation, HttpStatus.OK);
	
	}
	@RequestMapping(
			value = "/getTodayReservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reservation>> getTodayReservations() throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016-11-16 12:08
		String todayDate = dateFormat.format(date).split(" ")[0];
		Employee waiter = employeeService.findById(u.getId());
		Restaurant rest =waiter.getRestaurant();
		ArrayList<Reservation> reservations = reservationService.findByIdRestaurantAndDate(rest, todayDate);
		return new ResponseEntity<ArrayList<Reservation>>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getReservedTableForReservation/{idReservation}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tablee> getReservedTableForReservation(
			@PathVariable Long idReservation) throws Exception {
		
		System.out.println("pronasao ga");
		List<Tablee> tables = new ArrayList<Tablee>();
		Reservation reservation = reservationService.findOne(idReservation);
		tables = reservation.getReservedTables();
		
		
		System.out.println("tabele"+tables.size());
		return tables;
	}
	
	@RequestMapping(
			value = "/getTablesForReservation/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Tablee>> getTablesForReservation(@PathVariable Long id) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee waiter = employeeService.findById(u.getId());
		System.out.println("waiter= "+waiter.getEmail());
		Reservation reservation = reservationService.findOne(id);
		ArrayList<Tablee> tables = new ArrayList<Tablee>();
		ArrayList<AssignReon> assignReons = assignReonService.findByWaiter(waiter);
		Reon waiterReon = assignReons.get(0).getReon();//uzima uvijek prvi dodijeljen reon konobaru
		ArrayList<Tablee> waiterTables = tableService.findByReon(waiterReon);
		for(int i=0;i<waiterTables.size();i++){
			for(int j=0;j<reservation.getReservedTables().size();j++){
				Long waiterTable = waiterTables.get(i).getId();
				Long resTable = reservation.getReservedTables().get(j).getId();
				System.out.println("waiterTable= "+waiterTable+" resTable= "+resTable);
				if(waiterTable.equals(resTable)){
					tables.add(waiterTables.get(i));
					System.out.println("Dodao waiterTables" +waiterTable);
				}
			}
		}
		return new ResponseEntity<ArrayList<Tablee>>(tables, HttpStatus.OK);
	}
	
	
	
	


}
