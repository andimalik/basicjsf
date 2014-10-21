package id.co.skyforce.basicjsf.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "total_amount", nullable = false)
	private BigDecimal totalAmount;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "postal_code", nullable = true)
	private String postalCode;

	@Column(name = "street", nullable = true)
	private String street;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Customer getCustomer() {
		return customer;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void calculateTotalAmount() {
		BigDecimal subtotal = BigDecimal.ZERO;
		this.totalAmount = BigDecimal.ZERO;

		for (OrderDetail orderDetail : this.orderDetails) {
			subtotal = orderDetail.getPrice().multiply(
					new BigDecimal(orderDetail.getQuantity()));

			this.totalAmount = this.totalAmount.add(subtotal);
		}
	}

}
