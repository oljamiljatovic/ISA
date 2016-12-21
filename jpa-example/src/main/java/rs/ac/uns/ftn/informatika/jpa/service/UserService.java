package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface UserService {

	User findUserByUsernameAndPassword(String username, String password);
}
