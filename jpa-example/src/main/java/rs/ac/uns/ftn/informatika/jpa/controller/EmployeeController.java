package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;

@Controller 
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ManagerService managerService;
	
    /*@RequestMapping("/")
	public String Indexas() {
	
		return "index.html";
	}*/
	
	@GetMapping("/employees")
	@ResponseBody
	@Transactional(readOnly = true)
	public ArrayList<String> getEmployees() {
	
		ArrayList<String> employeesUsernames= new ArrayList<String>();
		ArrayList<Employee> employees = this.employeeService.getEmployees();
		
		for(int i = 0 ; i <employees.size(); i++){
			employeesUsernames.add(employees.get(i).getEmail());
			
		}
		
		return employeesUsernames;
	}
	
	
	@RequestMapping(
			value = "/getWaitersOfRestaurant",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Employee>> getWaitersOfRestaurant()  throws Exception {

		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		RestaurantManager rm = this.managerService.getManager(u.getEmail());
		ArrayList<Employee> empl = this.employeeService.getWaitersOfRestaurant("waiter",rm.getRestaurant());
		
		return new ResponseEntity<ArrayList<Employee>>(empl, HttpStatus.OK);
	}
	
}
