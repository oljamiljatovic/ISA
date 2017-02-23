package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.ReonService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;
import rs.ac.uns.ftn.informatika.jpa.service.TableService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;
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
	@Autowired
	private EmployeeService employeeService;
	
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
			value = "/uzmiReone",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reon>> uzmiReone()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		ArrayList<Reon> ar = this.reonService.findByRestaurant(r.getId());
		return new ResponseEntity<ArrayList<Reon>>(ar, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/izbrisiReone",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteReon(@RequestBody Reon r)  throws Exception {
		
		ArrayList<Tablee> table = this.tableService.findByReon(r.getId());
		for(int i=0; i<table.size(); i++){
			Long idd = table.get(i).getId();
			this.tableService.delete(idd);
		}
		
		this.reonService.delete(r);
	}
	
	@RequestMapping(
			value = "/updateReon",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reon> updateReon(@RequestBody Reon reon)  throws Exception {
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
		this.reonService.update(reon);
		
		ArrayList<Tablee> table = this.tableService.findByReon(r.getId());
		for(int i=0; i<table.size(); i++){
			Long idd = table.get(i).getId();
			this.tableService.delete(idd);
		}
		
		for(int i=0; i<reon.getNumberTable(); i++){
			Tablee tablee = new Tablee(reon.getId(),r.getId());
			this.tableService.createTable(tablee);
		}
		return new ResponseEntity<Reon>(reon, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/scheduleEmployee",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkSchedule> scheduleEmpolyee(@RequestBody WorkSchedule ws)  throws Exception {
		
		Employee e = this.employeeService.findById(ws.getWorker_id());
		ws.setWorker_name(e.getName());
		
		this.workScheduleService.createSchedule(ws);
		return new ResponseEntity<WorkSchedule>(ws, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiSmene",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<WorkSchedule>> uzmiSmene()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		ArrayList<WorkSchedule> fs = this.workScheduleService.findByRest(r.getId());
		return new ResponseEntity<ArrayList<WorkSchedule>>(fs, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/izbrisiSmene",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteShift(@RequestBody WorkSchedule ws)  throws Exception {
		
		this.workScheduleService.delete(ws);
	}
	
	@RequestMapping(
			value = "/updateScheduleEmployee",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkSchedule> updateSchedule(@RequestBody WorkSchedule ws)  throws Exception {
		this.workScheduleService.update(ws);
		return new ResponseEntity<WorkSchedule>(ws, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(
			value = "/assignReon",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssignReon> assignReon(@RequestBody AssignReon reon)  throws Exception {
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
			reon.setRestaurant(r.getId());
		}
		Employee e = this.employeeService.findById(reon.getWaiter_id());
		reon.setWaiter_name(e.getName());
		Reon rr = this.reonService.findOne(reon.getReon_id());
		reon.setReon_name(rr.getName());
		this.assignReonService.createAssignReon(reon);
		return new ResponseEntity<AssignReon>(reon, HttpStatus.OK);
	}
	

	@RequestMapping(
			value = "/uzmiDodelu",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<AssignReon>> uzmiKonfiguraciju()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		ArrayList<AssignReon> ar = this.assignReonService.findByRestaurant(r.getId());
		return new ResponseEntity<ArrayList<AssignReon>>(ar, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/izbrisiDodelu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteKonfig(@RequestBody AssignReon r)  throws Exception {
		
		this.assignReonService.delete(r.getId());
	}
	
}
