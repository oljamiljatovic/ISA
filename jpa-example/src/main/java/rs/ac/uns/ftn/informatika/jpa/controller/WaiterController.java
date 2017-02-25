package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

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
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ReonService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservedTablesService;
import rs.ac.uns.ftn.informatika.jpa.service.TableService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleService;

@Controller 
@RequestMapping("/waiterController")
public class WaiterController {
	@Autowired
	private ReonService reonService;
	@Autowired
	private WorkScheduleService workScheduleService;
	@Autowired
	private AssignReonService assignReonService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TableService tableService;
	@Autowired
	private ReservedTablesService reservedTablesService;
	
	@RequestMapping(
			value = "/getWorkSchedules",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<WorkSchedule>> getWorkSchedules()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<WorkSchedule> temp = new ArrayList<WorkSchedule>();
		if(u.getRole().equals("waiter") || u.getRole().equals("cook") || u.getRole().equals("barman")){
			temp = workScheduleService.findByWorker_id(u.getId());
			/*ArrayList<WorkSchedule> ws = workScheduleService.findAll();
			Long id =u.getId();
			Long worker_id = null;
			for(int i=0;i<ws.size();i++){
				worker_id = ws.get(i).getWorker_id();
				if(id.equals(worker_id)){
					temp.add(ws.get(i));
				}
			}*/
		}
		return new ResponseEntity<ArrayList<WorkSchedule>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getReons",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reon>> getReons()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Reon> temp = new ArrayList<Reon>();
		if(u.getRole().equals("waiter")){
			Employee employee = employeeService.findById(u.getId());
			/*ArrayList<Employee> employees = employeeService.getEmployees();
			Long restId = null;
			for(int i = 0;i<employees.size();i++){
				System.out.println("EID"+employees.get(i).getId());
				System.out.println("UID"+u.getId());
				if(employees.get(i).getId().equals(u.getId())){
					restId = employees.get(i).getRestaurant();
					System.out.println("Nasao rest"+restId);
					break;
				}
			}*/
			temp = reonService.getReonsOfRestorans(employee.getRestaurant());

		}
		return new ResponseEntity<ArrayList<Reon>>(temp, HttpStatus.OK);
	}
	///vraca stolove koji pripadaju reonu kome pripada konbar
	@RequestMapping(
			value = "/getTables",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Tablee>> getTables()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Tablee> temp = new ArrayList<Tablee>();
		if(u.getRole().equals("waiter")){
			Employee employee = employeeService.findById(u.getId());
			ArrayList<Reon> reons= reonService.getReonsOfRestorans(employee.getRestaurant());
			Long reonId = reons.get(0).getId();
			Long restaurantId = employee.getRestaurant();
			System.out.println("reonId" + reonId);
			temp = tableService.findByReonAndRestaurant(reonId, restaurantId);
		}
		return new ResponseEntity<ArrayList<Tablee>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getAssignReons",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<AssignReon>> getAssignReons()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<AssignReon> temp = new ArrayList<AssignReon>();
		if(u.getRole().equals("waiter")){
			temp = assignReonService.findByWaiter_id(u.getId());
			/*ArrayList<AssignReon> assignReons = assignReonService.findAll();
			for(int j = 0;j<assignReons.size();j++){
				if(assignReons.get(j).getWaiter_id().equals(u.getId())){
					temp.add(assignReons.get(j));
					break;
				}
			}*/
		}
		return new ResponseEntity<ArrayList<AssignReon>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getEmployee",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee temp = new Employee();
		temp = employeeService.findById(u.getId());
		return new ResponseEntity<Employee>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> update(
			@RequestBody Employee employee) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee foundedEmployee = employeeService.findById(u.getId());
		foundedEmployee.setDateBirth(employee.getDateBirth());
		foundedEmployee.setEmail(employee.getEmail());
		foundedEmployee.setConfNumber(employee.getConfNumber());
		foundedEmployee.setShoeNumber(employee.getShoeNumber());
		foundedEmployee.setPassword(employee.getPassword());
		foundedEmployee.setFirstLog(employee.getFirstLog());
		Employee changedEmployee = employeeService.update(foundedEmployee, u.getId());
		return new ResponseEntity<Employee>(changedEmployee, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateFirstLog",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateFirstLog(
			@RequestBody Employee employee) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee foundedEmployee = employeeService.findById(u.getId());
		foundedEmployee.setPassword(employee.getPassword());
		foundedEmployee.setFirstLog(employee.getFirstLog());
		Employee changedEmployee = employeeService.update(foundedEmployee, u.getId());
		return new ResponseEntity<Employee>(changedEmployee, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getTablesForRestaurant",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Tablee>> getTablesForRestaurant()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Tablee> temp = new ArrayList<Tablee>();
		Employee foundedEmployee = employeeService.findById(u.getId());
		temp = tableService.findByRestaurant(foundedEmployee.getRestaurant());
		return new ResponseEntity<ArrayList<Tablee>>(temp, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/getTablesForReservation",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Tablee>> getTablesForReservation(
			@RequestBody Reservation reservation)  throws Exception {
		
	
		ArrayList<Tablee> temp = new ArrayList<Tablee>();
	
		temp = tableService.findByRestaurant(reservation.getIdRestaurant());
		System.out.println("Pronasao je stolove "+temp.size() );
		return new ResponseEntity<ArrayList<Tablee>>(temp, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/getReonsForReservation",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Reon>> getReonsForReservation(
			@RequestBody Reservation reservation)  throws Exception {
	
		ArrayList<Reon> temp = reonService.getReonsOfRestorans(reservation.getIdRestaurant());
		System.out.println("Broj vracenih reona"+temp.size());
		
		return new ResponseEntity<ArrayList<Reon>>(temp, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(
			value = "/getReservedTables",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ReservedTables>> getReservedTables(
			@RequestBody Reservation reservation)  throws Exception {
		
		String wantedDate = reservation.getDate();
		String wantedTime = reservation.getTime();
		
		String[] time = wantedTime.split(":");
		
		int hour = Integer.parseInt(time[0]);
		int minut = Integer.parseInt(time[1]);
		
		
		List<ReservedTables> datetables = reservedTablesService.findReservedTablesByDate(wantedDate);
		List<ReservedTables> timetables = new ArrayList<ReservedTables>();
		System.out.println("Broj zauzetih"+ datetables.size());
		
		for(int i = 0; i<datetables.size(); i++){
			String[] time2 = datetables.get(i).getTime().split(":");
			int hour2 = Integer.parseInt(time2[0]);
			int minut2 = Integer.parseInt(time2[1]);
			
			if((hour >= hour2 && hour<hour2+datetables.get(i).getDuration()) || 
			(hour + reservation.getDuration() > hour2 && hour + reservation.getDuration()<=hour2+datetables.get(i).getDuration())){
				timetables.add(datetables.get(i));
			}
		}
		
		System.out.println("Sa vremenom kao problem"+ timetables.size());
		return new ResponseEntity<ArrayList<ReservedTables>>((ArrayList<ReservedTables>) timetables, HttpStatus.OK);
	
	}
	
	
}
