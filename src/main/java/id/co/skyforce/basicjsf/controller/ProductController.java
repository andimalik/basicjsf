package id.co.skyforce.basicjsf.controller;

import java.util.List;

import id.co.skyforce.basicjsf.HibernateUtil;
import id.co.skyforce.basicjsf.domain.Category;
import id.co.skyforce.basicjsf.domain.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class ProductController {
	private Product product;
	private List<Category> categories;

	public ProductController() {
		String productIdInput = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("id");

		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		this.categories = (List<Category>) session.createQuery("from Category")
				.list();

		if (productIdInput != null) {
			Long productId = Long.valueOf(productIdInput);
			this.product = (Product) session.get(Product.class, productId);
		} else {
			Category category = new Category();

			this.product = new Product();
			this.product.setCategory(category);
		}

		transaction.commit();
		session.close();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String save() {
		Session session = HibernateUtil.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		
		session.saveOrUpdate(this.product);
		
		transaction.commit();
		session.close();
		
		return "list";
	}
}
