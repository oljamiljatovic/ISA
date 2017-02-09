package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;

public interface AssignReonService {

	public AssignReon createAssignReon(AssignReon assignReon);
	
	public ArrayList<AssignReon> findAll();
}
