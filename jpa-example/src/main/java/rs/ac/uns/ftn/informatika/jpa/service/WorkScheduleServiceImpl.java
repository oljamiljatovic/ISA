package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.repository.WorkScheduleRepository;

@Service
@Transactional
public class WorkScheduleServiceImpl implements WorkScheduleService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Override
	public WorkSchedule createSchedule(WorkSchedule ws) {
		return this.workScheduleRepository.save(ws);
	}

	@Override
	public ArrayList<WorkSchedule> findByWorker_id(Long worker_id) {
		return workScheduleRepository.findByWorker_id(worker_id);
	}

	@Override
	public ArrayList<WorkSchedule> findAll() {
		return this.workScheduleRepository.findAll();
	}

	@Override
	public void delete(WorkSchedule ws) {
		this.workScheduleRepository.delete(ws.getId());
	}

	@Override
	public ArrayList<WorkSchedule> findByRest(Long rest) {
		return this.workScheduleRepository.findByRest(rest);
	}

	@Override
	public void update(WorkSchedule ws) {
		
		this.workScheduleRepository.updateMeal(ws.worker_id, ws.dateStart, ws.dateEnd, 
				ws.shift, ws.getId());
	}

}
