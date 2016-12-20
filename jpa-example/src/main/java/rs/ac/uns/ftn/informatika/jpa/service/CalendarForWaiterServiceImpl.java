package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.CalendarForWaiter;
import rs.ac.uns.ftn.informatika.jpa.repository.CalendarForWaiterRepository;

@Service
@Transactional
public class CalendarForWaiterServiceImpl implements CalendarForWaiterService{
	@Autowired
	private CalendarForWaiterRepository calendarForWaiterRepository;
	
	@Override
	public ArrayList<CalendarForWaiter> getCalendarForWaiters() {
		
		return this.calendarForWaiterRepository.findAll();
	}
}
