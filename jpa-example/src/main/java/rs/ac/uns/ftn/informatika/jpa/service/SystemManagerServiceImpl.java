package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.users.SystemManager;
import rs.ac.uns.ftn.informatika.jpa.repository.SystemManagerRepository;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class SystemManagerServiceImpl implements SystemManagerService {
	
	@Autowired
	private SystemManagerRepository smRepository;
	
	@Override
	public SystemManager addManager(SystemManager manag) {
		return this.smRepository.save(manag);
	}

}
