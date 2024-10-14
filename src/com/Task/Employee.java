package com.Task;

public class Employee {
	private int id;
	private String name;
	private String uname;
	private String pwd;
	private String date;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String uname, String pwd, String date) {
		super();
		this.id = id;
		this.name = name;
		this.uname = uname;
		this.pwd = pwd;
		this.date = date;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", uname=" + uname + ", pwd=" + pwd + ", date=" + date + "]";
	}

	public static void closeConnection() {
		// TODO Auto-generated method stub

	}

}
