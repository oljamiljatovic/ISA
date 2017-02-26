package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;

public interface WorkScheduleService {
	
	public WorkSchedule createSchedule(WorkSchedule ws);
	
	public ArrayList<WorkSchedule> findByWorker_id(Long worker_id);
	
	public ArrayList<WorkSchedule> findAll();
	
	public void delete(WorkSchedule ws);
	
	public ArrayList<WorkSchedule> findByRestaurant(Restaurant rest);
	
	public void update(WorkSchedule ws);

}
