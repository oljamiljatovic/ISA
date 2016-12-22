package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.WorkingArea;

public interface WorkingAreaRepository  extends PagingAndSortingRepository<WorkingArea, Long>{
	public ArrayList<WorkingArea> findAll();
}