package rs.ac.uns.ftn.informatika.jpa.service;

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

}