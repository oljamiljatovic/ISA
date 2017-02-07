package rs.ac.uns.ftn.informatika.jpa.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;

public interface TableRepository extends PagingAndSortingRepository<Tablee, Long> {
	
	
}
