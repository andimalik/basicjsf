package id.co.skyforce.basicjsf.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;

import id.co.skyforce.basicjsf.HibernateUtil;
import id.co.skyforce.basicjsf.domain.Product;

@ManagedBean
public class ProductListController {
	private List<Product> products;

	public ProductListController() {
		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		this.products = session.createQuery("from Product").list();

		transaction.commit();
		session.close();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
