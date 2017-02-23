package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserService {

	User findUserByEmailAndPassword(String email, String password);
	
	User createNew(User user);
	
	User update(User user, Long id);
	

	public void updateUserPassword(User dr);
	
	public User findOne(Long id);
}
