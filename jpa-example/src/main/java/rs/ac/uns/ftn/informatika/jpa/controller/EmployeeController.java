package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;

@Controller 
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
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
}
