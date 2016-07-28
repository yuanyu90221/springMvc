package com.test.info;

import java.util.List;

import com.test.vo.Employee;

public interface EmployeeDAO {

	public void save(Employee employee);
	
	public Employee getById(int id);
	
	public void update(Employee employee);
	
	public void deleteById(int id);
	
	public List<Employee> getAll();
}
