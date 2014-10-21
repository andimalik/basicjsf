package id.co.skyforce.basicjsf;

import id.co.skyforce.basicjsf.domain.Address;
import id.co.skyforce.basicjsf.domain.Customer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

public class CustomerServlet extends HttpServlet {
	Session session;
	CustomerManager customerManager;

	public CustomerServlet() {
		this.openSession();
		this.customerManager = new CustomerManager(this.session);
	}

	@Override
	public void destroy() {
		super.destroy();
		this.closeSession();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action == null) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			req.setAttribute("dateFormatter", dateFormatter);
			req.setAttribute("customerList",
					this.customerManager.getCustomerList());
			req.getRequestDispatcher("customer/list_customer.jsp").forward(req, resp);
		} else if (action.equals("edit")) {
			this.edit(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action.equals("add")) {
			this.add(req, resp);
		} else if (action.equals("update")) {
			this.update(req, resp);
		} else if (action.equals("delete")) {
			this.delete(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/customer");
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customer customer = new Customer();
		CustomerManager.setCustomerDataFromInput(customer, req, resp);

		this.customerManager.add(customer);
		resp.sendRedirect(req.getContextPath() + "/customer");
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long customerId = Long.parseLong(req.getParameter("customer_id"));
		Customer customer = this.customerManager.getCustomer(customerId);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		if (customer == null) {
			resp.sendRedirect(req.getContextPath() + "/customer");
		} else {
			req.setAttribute("customer", customer);
			req.setAttribute("dateFormatter", dateFormatter);
			req.getRequestDispatcher("customer/edit_customer.jsp").forward(req, resp);
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long customerId = Long.parseLong(req.getParameter("customer_id"));
		Customer customer = this.customerManager.getCustomer(customerId);

		if (customer == null) {
			req.setAttribute("errorMessage", "Customer (ID " + customerId
					+ ") not found.");
			req.getRequestDispatcher("customer/edit_customer.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMessage", null);

			this.customerManager.update(customer, req, resp);

			resp.sendRedirect(req.getContextPath() + "/customer");
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long customerId = Long.parseLong(req.getParameter("customer_id"));
		Customer customer = this.customerManager.getCustomer(customerId);

		if (customer != null) {
			this.customerManager.delete(customer);
		}

		resp.sendRedirect(req.getContextPath() + "/customer");
	}

	private void openSession() {
		this.session = HibernateUtil.openSession();
	}

	private void closeSession() {
		this.session.close();
	}

}
