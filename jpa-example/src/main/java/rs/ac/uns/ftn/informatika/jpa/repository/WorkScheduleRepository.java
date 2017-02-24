package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import java.lang.Long;
import java.util.List;

public interface WorkScheduleRepository extends PagingAndSortingRepository<WorkSchedule, Long> {
	
	@Query("select w from WorkSchedule w where w.worker_id = ?1")
	ArrayList<WorkSchedule> findByWorker_id(Long worker_id);
	
	public ArrayList<WorkSchedule> findAll(); 

	void delete(Long entity);
	
	@Query("select w from WorkSchedule w where w.rest = ?1")
	ArrayList<WorkSchedule> findByRest(Long rest);
	
	@Modifying
	@Query("update WorkSchedule set worker_id = ?, dateStart = ?, dateEnd = ?, shift = ? where id = ? ")
	public void updateMeal(Long worker, String start, String end, String shift, Long id);
	
}
