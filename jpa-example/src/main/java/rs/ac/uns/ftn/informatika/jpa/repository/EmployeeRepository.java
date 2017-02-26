package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import java.lang.String;
import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	public ArrayList<Employee> findAll(); 
	public ArrayList<Employee> findByRestaurant(Long restaurant);
	public Employee findById(Long id);
	public ArrayList<Employee> findByRoleAndRestaurant(String role, Long id);
	
}
