package model;

public class Person {

	private int id;
	private String name;
	private String mobile;
	private String email;

	public Person(int id, String name, String mobile, String email) {
		this(name, mobile, email);
		this.id = id;
	}

	public Person() {
	}

	public Person(String name, String mobile, String email) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
