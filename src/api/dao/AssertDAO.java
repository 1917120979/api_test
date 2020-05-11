package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Assert;

public class AssertDAO extends BaseDAO{
	public boolean add(Assert bean) {
		if (null != bean.getTestcase()) {
			String sql = "insert into assert_info values(null,null,?,?,?,?,?)";
			Object[] params = {bean.getTestcase().getId(), bean.getAssertName(),bean.getAssertRegular(),
					bean.getExpectValue(), bean.getComments() };
			return super.update(sql, params);
		}else {
			String sql = "insert into assert_info values(null,?,null,?,?,?,?)";
			Object[] params = {bean.getApi().getId(),bean.getAssertName(),bean.getAssertRegular(),
					bean.getExpectValue(), bean.getComments() };
			return super.update(sql, params);
		}
	}
	
	public boolean delete(int id) {
		String sql = "delete from assert_info where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(Assert bean) {
		String sql = "update assert_info set assert_name = ?, assert_regular = ? ,expect_value = ?,comments = ? where id = ?";
		Object[] params = {bean.getAssertName(),bean.getAssertRegular(),bean.getExpectValue(), bean.getComments(),bean.getId()};
		return super.update(sql, params);
	}
	
	public Assert get(int id) {
		String sql = "select * from assert_info where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);		
		try {
			while(rs.next()) {
				Assert bean = new Assert();
				bean.setId(id);
				bean.setAssertName(rs.getString("assert_name"));
				bean.setAssertRegular(rs.getString("assert_regular"));
				bean.setExpectValue(rs.getString("expect_value"));
				bean.setComments(rs.getString("comments"));
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public List<Assert> list(int aid, int tid) {
		List<Assert> beans = new ArrayList<Assert>();
		String sql = "";
		if (aid == 0) {
			sql = "select * from assert_info where tid = "+tid;
		}
		if (tid == 0) {
			sql = "select * from assert_info where aid = "+aid;
		}		
		ResultSet rs = super.query(sql);
		try {
			while(rs.next()) {
				Assert bean = new Assert();
				bean.setId(rs.getInt("id"));
				bean.setAssertName(rs.getString("assert_name"));
				bean.setAssertRegular(rs.getString("assert_regular"));
				bean.setExpectValue(rs.getString("expect_value"));
				bean.setComments(rs.getString("comments"));
				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return beans;
	}
	
}
