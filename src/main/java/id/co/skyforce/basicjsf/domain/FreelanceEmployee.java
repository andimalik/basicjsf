package id.co.skyforce.basicjsf.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class FreelanceEmployee extends Employee {

	@Column(name = "rate_per_hour")
	private BigDecimal ratePerHour;

	public BigDecimal getRatePerHour() {
		return this.ratePerHour;
	}

	public void setRatePerHour(BigDecimal ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
}
