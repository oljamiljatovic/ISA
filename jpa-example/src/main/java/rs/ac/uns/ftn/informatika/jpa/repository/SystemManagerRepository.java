package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.users.SystemManager;

public interface SystemManagerRepository extends PagingAndSortingRepository<SystemManager, Long> {

}
