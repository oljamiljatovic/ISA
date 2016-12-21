package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.HotelRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return userRepository.findUserByUsernameAndPassword(username, password);
	}

}
