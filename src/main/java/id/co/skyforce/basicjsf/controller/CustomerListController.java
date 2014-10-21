package id.co.skyforce.basicjsf.controller;

import java.util.List;

import id.co.skyforce.basicjsf.HibernateUtil;
import id.co.skyforce.basicjsf.domain.Customer;

import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class CustomerListController {
	private List<Customer> customers;

	public CustomerListController() {
		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		this.customers = session.createQuery("from Customer").list();

		transaction.commit();
		session.close();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
