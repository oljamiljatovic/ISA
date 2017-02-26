package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	public ArrayList<Employee> findAll(); 
	public ArrayList<Employee> findByRestaurant(Restaurant restaurant);
	public Employee findById(Long id);
	public ArrayList<Employee> findByRoleAndRestaurant(String role, Restaurant rest);
	
}
