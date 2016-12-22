package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{
	public ArrayList<Order> findAll();
}
