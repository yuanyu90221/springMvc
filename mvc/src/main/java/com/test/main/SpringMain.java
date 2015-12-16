package com.test.main;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.info.EmployeeDAO;
import com.test.vo.Employee;

public class SpringMain {

	public static void main(String[] args) {
		// Get the Spring Context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-bean.xml");
		// Get the EmployeeDAO Bean
		EmployeeDAO employeeDAO = ctx.getBean("employeeDAO", EmployeeDAO.class);
		
		// Run some test for JDBC
		Employee emp = new Employee();
		int rand = new Random().nextInt(1000);
		emp.setId(rand);
		emp.setName("test");
		emp.setRole("java developer");
		
		// Create
		employeeDAO.save(emp);
		System.out.println("Employee Retrieved::"+emp);
		
		// Update
		emp.setRole("tonyStar");
		employeeDAO.update(emp);
		
		// Get All
		List<Employee> empList = employeeDAO.getAll();
		System.out.println(empList);
		
		//
		employeeDAO.deleteById(rand);
		ctx.close();
		System.out.println("DONE");
	}

}
