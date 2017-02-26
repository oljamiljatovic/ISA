package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;


public interface EmployeeService {

	public ArrayList<Employee> getEmployees();
	public Employee addEmployee(Employee e);
	public ArrayList<Employee> getEmployeesOfRestaurant(Restaurant rest);
	public ArrayList<Employee> getWaitersOfRestaurant(String role, Restaurant rest);
	public Employee findById(Long id);
	public Employee update(Employee employee, Long id);
}
