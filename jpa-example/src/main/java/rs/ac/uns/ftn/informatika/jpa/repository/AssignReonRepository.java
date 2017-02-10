package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;

public interface AssignReonRepository extends PagingAndSortingRepository<AssignReon, Long>  {
	
	public ArrayList<AssignReon> findAll();
}
