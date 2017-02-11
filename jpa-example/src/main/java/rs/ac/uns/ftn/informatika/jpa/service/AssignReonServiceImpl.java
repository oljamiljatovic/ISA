package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.repository.AssignReonRepository;
@Service
@Transactional
public class AssignReonServiceImpl implements AssignReonService{

	@Autowired
	private AssignReonRepository assignReonRepository;
	
	@Override
	public AssignReon createAssignReon(AssignReon assignReon) {
		return this.assignReonRepository.save(assignReon);
	}

	@Override
	public ArrayList<AssignReon> findAll() {
		return assignReonRepository.findAll();
	}

	@Override
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id) {
		return assignReonRepository.findByWaiter_id(waiter_id);
	}

}
