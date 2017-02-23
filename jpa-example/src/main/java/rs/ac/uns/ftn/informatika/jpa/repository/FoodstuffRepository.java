package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Foodstuff;

public interface FoodstuffRepository extends PagingAndSortingRepository<Foodstuff, Long> {


	public ArrayList<Foodstuff> findAll();
}
