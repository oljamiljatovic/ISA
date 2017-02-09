package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface WorkScheduleRepository extends PagingAndSortingRepository<WorkSchedule, Long> {
	
	@Query("select w from WorkSchedule w where w.worker_id = ?1")
	WorkSchedule findWorkScheduleByWorker_id(Long worker_id);
	
	public ArrayList<WorkSchedule> findAll(); 
}
