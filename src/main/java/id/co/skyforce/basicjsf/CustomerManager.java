package id.co.skyforce.basicjsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import id.co.skyforce.basicjsf.domain.Address;
import id.co.skyforce.basicjsf.domain.Customer;

public class CustomerManager {
	private Session session;
	private Transaction transaction;
	private Query query;
	private List<Customer> customerList;

	public CustomerManager(Session session) {
		this.session = session;
		this.customerList = this.queryCustomerList();
	}

	public Session getSession() {
		return session;
	}

	public List<Customer> getCustomerList() {
		return this.customerList;
	}

	public Customer getCustomer(Long customerId) {
		for (Customer customer : this.customerList) {
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}

		return null;
	}

	public void add(Customer customer) {
		this.beginTransaction();

		this.customerList.add(customer);
		this.session.save(customer);

		this.commitTransaction();
	}

	public void update(Customer customer, HttpServletRequest req,
			HttpServletResponse resp) {
		this.beginTransaction();

		CustomerManager.setCustomerDataFromInput(customer, req, resp);
		this.session.save(customer);

		this.commitTransaction();
	}

	public void delete(Customer customer) {
		this.beginTransaction();

		this.customerList.remove(customer);
		this.session.delete(customer);

		this.commitTransaction();
	}

	public static void setCustomerDataFromInput(Customer customer,
			HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String birthDateInput = req.getParameter("birth_date");
		String mobilePhone = req.getParameter("mobile_phone");
		String homePhone = req.getParameter("home_phone");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String postalCode = req.getParameter("postal_code");

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;

		try {
			birthDate = dateFormatter.parse(birthDateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		customer.setBirthDate(birthDate);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setHomePhone(homePhone);
		customer.setMobileNo(mobilePhone);
		customer.setPassword(password);

		Address address;
		if (customer.getAddress() == null) {
			address = new Address();
		} else {
			address = customer.getAddress();
		}

		address.setCity(city);
		address.setPostalCode(postalCode);
		address.setStreet(street);

		customer.setAddress(address);
	}

	private void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	private void commitTransaction() {
		this.transaction.commit();
	}

	private List<Customer> queryCustomerList() {
		this.query = session.createQuery("from Customer");
		return query.list();
	}
}
