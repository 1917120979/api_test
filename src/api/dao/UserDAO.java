package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.User;

public class UserDAO extends BaseDAO{
	public boolean add(User bean) {
		String sql = "insert into user_info values(null,?,?,?)";
		Object[] params = {bean.getUsername(), bean.getPassword(), bean.getRole()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from user_info where id ="+id;
		return super.update(sql);
	}
	
	public boolean update(User bean) {
		String sql = "update user_info set password = ? ,role = ? where id = ?";
		Object[] params = {bean.getPassword(), bean.getRole(), bean.getRole()};
		return super.update(sql, params);
	}
	
	public User get(int id) {
		String sql = "select * from user_info where id ="+id;
		ResultSet rs = super.query(sql);		
		try {
			while (rs.next()) {
				User bean = new User();
				bean.setId(id);
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getInt("role"));
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public User getUsername(String username) {
		String sql = "select * from user_info where username = ?";
		Object[] params = {username};
		ResultSet rs = super.query(sql, params);		
		try {
			while (rs.next()) {
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getInt("role"));
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public boolean isExsit(String username) {
		if (null != getUsername(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<User> list(){
		String sql = "select * from user_info where id >1";
		List<User> beans = new ArrayList<User>();
		ResultSet rs = super.query(sql);		
		try {
			while (rs.next()) {
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getInt("role"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return beans;
	}
	
	public int getTotal() {
		String sql = "select count(*) from user_info";
		return super.getTotal(sql);
	}
}
