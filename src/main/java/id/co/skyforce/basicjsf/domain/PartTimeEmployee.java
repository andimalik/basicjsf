package id.co.skyforce.basicjsf.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T")
public class PartTimeEmployee extends Employee {
	@Column(name = "work_hour")
	private int workHour;

	public int getWorkHour() {
		return this.workHour;
	}

	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}
}
