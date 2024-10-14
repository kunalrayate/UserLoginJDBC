package com.Task;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

	static Scanner sc = new Scanner(System.in);
	private static String path = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3307/logindb";
	private static Connection con = null;
	private static PreparedStatement st = null;
	private static String user = "root";
	private static String psw = "root";

	public static PreparedStatement createConnection(String query) {
		try {
			Class.forName(path);
			con = DriverManager.getConnection(url, user, psw);
			st = con.prepareStatement(query);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return st;
	}

	public static void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void createDatabase(String dtname) {
		String query = "CREATE SCHEMA " + dtname;
		try {
			Class.forName(path);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/", user, psw);
			con.prepareStatement(query).execute();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		Employee.closeConnection();
	}

	public static void createTable() {
		String query = "CREATE TABLE " + "login"
				+ "(Id INT PRIMARY KEY AUTO_INCREMENT,Name VARCHAR(50),Uname VARCHAR(50),Pwd VARCHAR(20),Adhar LONGBLOB,CreatedAt DATETIME DEFAULT now())";
		try {
			createConnection(query).executeUpdate();
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
		closeConnection();
//		System.out.println("Table not created..");
	}

	public static void singUp() {
		int count = 0;
		try {
			System.out.print("Enter Full Name : ");
			String name = sc.nextLine();
			System.out.println();
			System.out.print("Enter User Name : ");
			String uname = sc.nextLine();
			System.out.print("\nEnter Password : ");
			String ps = sc.nextLine();
			String query = "INSERT INTO login(name,uname,pwd)VALUES('" + name + "','" + uname + "','" + ps + "')";
			count = createConnection(query).executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		closeConnection();
		if (count > 0) {
			System.out.println("Employee Added..");
		} else {
			System.out.println("Not Inserted...");
		}
	}

	public static boolean login(String uname, String pass) {
		List<Employee> l1 = new ArrayList<Employee>();
		String query = "SELECT * FROM login";
		try {
			ResultSet rs = createConnection(query).executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String un = rs.getString("uname");
				String ps = rs.getString("Pwd");
				String date = rs.getString("CreatedAt");
				Employee e1 = new Employee(id, name, un, ps, date);
				l1.add(e1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		closeConnection();
		for (Employee emp : l1) {
			if (emp.getUname().equals(uname) && emp.getPwd().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public static List<Employee> showUsers() {
		List<Employee> l1 = new ArrayList<Employee>();
		String query = "SELECT * FROM login";
		try {
			ResultSet rs = createConnection(query).executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("name");
				String un = rs.getString("Uname");
				String ps = rs.getString("pwd");
				String date = rs.getString("CreatedAt");
				Employee e1 = new Employee(id, name, un, ps, date);
				l1.add(e1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		closeConnection();
		return l1;
	}

	public static void menu() {
//		createTable();

		System.out.println("WELCOME TO USERAPP :");
		while (true) {
			System.out.println("\n1) Sign Up\n2) Login\n3) Show All\n4)Exit");
			System.out.print("\nEnter choice : ");
			int n = sc.nextInt();
			sc.nextLine();
			switch (n) {
			case 1:
				singUp();
				break;
			case 2:
				System.out.print("Enter user name : ");
				String un = sc.nextLine();
				System.out.print("Enter password : ");
				String ps = sc.nextLine();
				if (login(un, ps)) {
					System.out.println("Login Successfully..");
				} else
					System.out.println("Invailid User name Or Password !");
				break;
			case 3:
				List<Employee> allEmployee = showUsers();
				for (Employee employee : allEmployee) {
					System.out.printf("%-7d%-15s%-13s\n", employee.getId(), employee.getUname(), employee.getPwd());
				}
				break;
			case 4:
				System.out.println("Thank you for visiting...");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		menu();
	}

}