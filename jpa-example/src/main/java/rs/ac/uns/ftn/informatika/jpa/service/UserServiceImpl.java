package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findUserByEmailAndPassword(email, password);
	}
	
	public User createNew(User user) {
		return userRepository.save(user);
	}

	
	public User update(User user, Long id) {
		user.setId(id);
		return userRepository.save(user);
	}

	@Override
	public void updateUserPassword(User dr) {
		this.userRepository.updateUser(dr.getPassword(), dr.getId());
	}
	

}
