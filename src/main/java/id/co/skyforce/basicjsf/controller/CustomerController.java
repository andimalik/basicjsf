package id.co.skyforce.basicjsf.controller;

import id.co.skyforce.basicjsf.HibernateUtil;
import id.co.skyforce.basicjsf.domain.Address;
import id.co.skyforce.basicjsf.domain.Customer;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class CustomerController {
	private Long customerId;
	private Long addressId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String mobileNo;
	private String homePhone;
	private Character gender;
	private String salutation;

	private String street;
	private String city;
	private String postalCode;

	public CustomerController() {
		String customerIdInput = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("id");

		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		if (customerIdInput != null) {
			Long customerId = Long.valueOf(customerIdInput);
			Customer customer = (Customer) session.get(Customer.class,
					customerId);

			this.customerId = customer.getId();
			this.email = customer.getEmail();
			this.password = customer.getPassword();
			this.firstName = customer.getFirstName();
			this.lastName = customer.getLastName();
			this.birthDate = customer.getBirthDate();
			this.mobileNo = customer.getMobileNo();
			this.homePhone = customer.getHomePhone();
			this.gender = customer.getGender();
			this.salutation = customer.getSalutation();

			this.addressId = customer.getAddress().getId();
			this.street = customer.getAddress().getStreet();
			this.city = customer.getAddress().getCity();
			this.postalCode = customer.getAddress().getPostalCode();
		}

		transaction.commit();
		session.close();
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String save() {
		Customer customer = new Customer();
		Address address = new Address();

		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		customer.setId(this.customerId);
		customer.setBirthDate(this.birthDate);
		customer.setEmail(this.email);
		customer.setFirstName(this.firstName);
		customer.setLastName(this.lastName);
		customer.setHomePhone(this.homePhone);
		customer.setMobileNo(this.mobileNo);
		customer.setPassword(this.password);
		customer.setGender(this.gender);
		customer.setSalutation(this.salutation);

		address.setId(this.addressId);
		address.setId(this.addressId);
		address.setCity(this.city);
		address.setPostalCode(this.postalCode);
		address.setStreet(this.street);

		customer.setAddress(address);

		session.saveOrUpdate(customer);

		transaction.commit();

		return "list";
	}
}
