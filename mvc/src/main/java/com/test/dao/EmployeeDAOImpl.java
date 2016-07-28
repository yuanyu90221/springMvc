package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.test.info.EmployeeDAO;
import com.test.vo.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class EmployeeDAOImpl implements EmployeeDAO {

	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Employee employee) {
		String query = "insert into Employee(id, name, role) values(?, ? ,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			int index = 1;
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(index++, employee.getId()); 
			ps.setString(index++, employee.getName());
			ps.setString(index++, employee.getRole());
			int out = ps.executeUpdate();
			if(out != 0){
				System.out.println("Employee saved with id  = "+ employee.getId());
			}
			else {
				System.out.println("Employee saved failed with id = "  + employee.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Employee getById(int id) {
		String query = "select name, role from Employee where id = ?";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				System.out.println("Employee Found :: " + emp);
			}
			else {
				System.out.println("No Employee found with id="+id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emp;
	}

	@Override
	public void update(Employee employee) {
		String query = "update Employee set name=?, role=? where id =?";
		Connection con = null;
        PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, employee.getName());
			ps.setString(index++, employee.getRole());
			ps.setInt(index++, employee.getId());
			int out = ps.executeUpdate();
			if(out != 0){
				System.out.println("Employee updated with id = " + employee.getId());
			}
			else{
				System.out.println("No Employee found with id="+employee.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteById(int id) {
		String query = "delete from Employee where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if( out != 0){
				System.out.println("Employ delete with id = " + id);
			}
			else{
				System.out.println("No Employee found with id="+id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public List<Employee> getAll() {
		String query = "select id, name, role from Employee";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return empList;
	}

}
