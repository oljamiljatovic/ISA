package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ArrayList<Employee> getEmployees() {
		
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee addEmployee(Employee e) {
		return this.employeeRepository.save(e);
	}

	@Override
	public ArrayList<Employee> getEmployeesOfRestaurant(Restaurant rest) {
		
		return this.employeeRepository.findByRestaurant(rest);
	}

	@Override
	public ArrayList<Employee> getWaitersOfRestaurant(String role, Restaurant id) {
		return this.employeeRepository.findByRoleAndRestaurant(role, id);
	}

	@Override
	public Employee findById(Long id) {
		return this.employeeRepository.findById(id);
	}

	@Override
	public Employee update(Employee employee, Long id) {
		employee.setId(id);
		return employeeRepository.save(employee);
	}

	
	
}
