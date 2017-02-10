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
	public WorkSchedule findWorkScheduleByWorker_id(Long worker_id) {
		return workScheduleRepository.findWorkScheduleByWorker_id(worker_id);
	}

	@Override
	public ArrayList<WorkSchedule> findAll() {
		return this.workScheduleRepository.findAll();
	}

}
