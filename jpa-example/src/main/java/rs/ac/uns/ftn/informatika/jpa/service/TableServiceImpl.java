package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.repository.TableRepository;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepository;
	
	
	@Override
	public Tablee createTable(Tablee table) {
		
		 return this.tableRepository.save(table);
	}

}
