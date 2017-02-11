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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.service.AssignReonService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ReonService;
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
		Employee changedEmployee = employeeService.update(foundedEmployee, u.getId());
		return new ResponseEntity<Employee>(changedEmployee, HttpStatus.OK);
	}
}
