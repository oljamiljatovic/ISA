package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;

public interface WorkScheduleRepository extends PagingAndSortingRepository<WorkSchedule, Long> {

}
