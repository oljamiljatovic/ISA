package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface EmployeeService {

	public ArrayList<Employee> getEmployees();
	public Employee addEmployee(Employee e);
	public ArrayList<Employee> getEmployeesOfRestaurant(String rest);
	public ArrayList<Employee> getWaitersOfRestaurant(String rest);
}
