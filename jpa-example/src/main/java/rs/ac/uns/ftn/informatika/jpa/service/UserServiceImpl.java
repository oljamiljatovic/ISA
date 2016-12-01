package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Page<User> findCities(String criteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String name, String username) {
		Assert.notNull(name, "Naziv grada ne sme biti null");
		
		return this.userRepository.findByNameAndUsernameAllIgnoringCase(name, username);
	}
 
}
