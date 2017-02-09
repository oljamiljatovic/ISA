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
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.ReonService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;
import rs.ac.uns.ftn.informatika.jpa.service.TableService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleService;


@Controller
@RequestMapping("/restaurantManagerController")
public class RestaurantManagerController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TableService tableService;
	@Autowired
	private ReonService reonService;
	@Autowired
	private WorkScheduleService workScheduleService;
	@Autowired
	private AssignReonService assignReonService;
	@Autowired
	private DrinkService drinkService;
	
	@RequestMapping(
			value = "/updateRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant rest)  throws Exception {
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
		Long id = r.getId();
		rest.setId(id);
		this.restaurantService.updateRestaurant(rest);
		//this.restaurantService.deleteRestaurant(id);
		//this.restaurantService.addRestaurant(rest);
		//rm.setRestaurant(id);
		//this.managerService.updateManager(rm);
		//this.restaurantService.updateRestaurant(rest);
		/*if(rest.getDrinks().size()>1){
			System.out.println(rest.getDrinks().get(0));
			System.out.println(rest.getDrinks().get(1));
		}*/
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addEmployee",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmpolyee(@RequestBody Employee empl)  throws Exception {
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
		empl.setRestaurant(r.getId());
		this.employeeService.addEmployee(empl);
		System.out.println("-----------------------------"+empl.getId());
		return new ResponseEntity<Employee>(empl, HttpStatus.OK);
	}
	
	
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
			value = "/uzmiRadnike",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Employee>> uzmiRadnike()  throws Exception {
		
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
		ArrayList<Employee> temp= this.employeeService.getEmployeesOfRestaurant(r.getId());
		
		return new ResponseEntity<ArrayList<Employee>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiKonobare",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Employee>> uzmiKonobare()  throws Exception {
		
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
		ArrayList<Employee> temp= this.employeeService.getEmployeesOfRestaurant(r.getId());
		ArrayList<Employee> temp2 = new ArrayList<Employee>();
		
		for(int i=0; i<temp.size(); i++){
			if(temp.get(i).getRole().equals("waiter"))
				temp2.add(temp.get(i));
		}
		
		return new ResponseEntity<ArrayList<Employee>>(temp2, HttpStatus.OK);
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
		ArrayList<Reon> t= this.reonService.getReonsOfRestorans(r.getId());
		
		
		return new ResponseEntity<ArrayList<Reon>>(t, HttpStatus.OK);
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
	
	
	@RequestMapping(
			value = "/deleteDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDrink(@RequestBody Drink drink)  throws Exception {
		
		Drink dr = this.drinkService.getDrink(drink.getId());
		this.drinkService.deleteDrink(dr);
	}
}
