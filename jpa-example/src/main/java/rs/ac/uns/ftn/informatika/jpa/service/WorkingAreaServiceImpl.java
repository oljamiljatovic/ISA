package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkingArea;
import rs.ac.uns.ftn.informatika.jpa.repository.WorkingAreaRepository;

@Service
@Transactional
public class WorkingAreaServiceImpl implements WorkingAreaService{
	@Autowired
	private WorkingAreaRepository workingAreaRepository;
	
	@Override
	public ArrayList<WorkingArea> getWorkingAreas() {
		
		return this.workingAreaRepository.findAll();
	}
}
