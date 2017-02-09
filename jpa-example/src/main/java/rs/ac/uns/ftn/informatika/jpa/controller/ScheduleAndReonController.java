package rs.ac.uns.ftn.informatika.jpa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.ReonService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;
import rs.ac.uns.ftn.informatika.jpa.service.TableService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleService;


@Controller
@RequestMapping("/scheduleAndReonController")
public class ScheduleAndReonController {
	

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private TableService tableService;
	@Autowired
	private ReonService reonService;
	@Autowired
	private WorkScheduleService workScheduleService;
	@Autowired
	private AssignReonService assignReonService;
	
	@RequestMapping(
			value = "/addReon",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reon> addReon(@RequestBody Reon reon)  throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes)
				RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		
		reon.setRestaurant(r.getId());
		this.reonService.createReon(reon);
		
		for(int i=0; i<reon.getNumberTable(); i++){
			Tablee table = new Tablee(reon.getId(),r.getId());
			this.tableService.createTable(table);
		}
		return new ResponseEntity<Reon>(reon, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/scheduleEmployee",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkSchedule> scheduleEmpolyee(@RequestBody WorkSchedule ws)  throws Exception {
		
		this.workScheduleService.createSchedule(ws);
		return new ResponseEntity<WorkSchedule>(ws, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/assignReon",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssignReon> assignReon(@RequestBody AssignReon reon)  throws Exception {
		this.assignReonService.createAssignReon(reon);
		return new ResponseEntity<AssignReon>(reon, HttpStatus.OK);
	}

}
