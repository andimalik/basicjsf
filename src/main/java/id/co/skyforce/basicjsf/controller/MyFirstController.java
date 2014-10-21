package id.co.skyforce.basicjsf.controller;

import javax.faces.bean.ManagedBean;

//@ManagedBean(name = "x")
@ManagedBean
public class MyFirstController {
	private String name;

	private String message = "This is my first controller.";

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sayHello() {
		this.message = "Hello " + name;
	}

	public String sayHelloAgain() {
		this.message = "Hello hello " + name;
		return "output";
	}
}
