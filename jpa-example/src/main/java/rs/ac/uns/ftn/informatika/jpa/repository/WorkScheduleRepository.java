package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface WorkScheduleRepository extends PagingAndSortingRepository<WorkSchedule, Long> {
	
	ArrayList<WorkSchedule> findByWorker_id(Long worker_id);
	
	public ArrayList<WorkSchedule> findAll(); 

	void delete(Long entity);
	
	ArrayList<WorkSchedule> findByRestaurant(Restaurant rest);
	
	@Modifying
	@Query("update WorkSchedule set dateStart = ?, dateEnd = ?, shift = ? where id = ? ")
	public void updateSchedule(String start, String end, String shift, Long id);
	
}
