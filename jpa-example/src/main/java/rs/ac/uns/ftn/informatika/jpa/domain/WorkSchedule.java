package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WorkSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(unique=true, nullable=false)
	private Long id;
	@Column(nullable = false)
	public String dateStart;
	@Column(nullable = false)
	public String dateEnd;
	@Column(nullable = false)
	public Long worker_id;
	@Column(nullable = false)
	public String shift;
	
	public WorkSchedule() {
	}
	
	public WorkSchedule(String dateStart, String dateEnd, Long worker_id, String shift) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.worker_id = worker_id;
		this.shift = shift;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Long getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Long worker_id) {
		this.worker_id = worker_id;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
	
}
