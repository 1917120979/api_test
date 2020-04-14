package api.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import api.bean.Assert;

public class AssertDAO extends BaseDao{
	public boolean add(Assert bean) {
		String sql = "insert into api_assert values(null,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(), bean.getAssertExpress(), bean.getAssertExpect()};
		return super.update(sql, params, Assert.class);
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_assert where id = ?";
		Object[] params = {id};
		return super.update(sql, params, null);
	}
	
	public boolean update(Assert bean) {
		String sql = "update api_assert set assert_express = ? ,assert_expect = ? where id = ?";
		Object[] params = {bean.getAssertExpress(), bean.getAssertExpect(), bean.getId()};
		return super.update(sql, params, null);
	}
	
	public Assert get(int id) {
		String sql = "select * from api_assert where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Assert bean = new Assert();
		try {
			while(rs.next()) {
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				
				bean.setId(id);
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setAssertExpress(rs.getString("assert_express"));
				bean.setAssertExpect(rs.getString("assert_expect"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return bean;
	}
	
	public List<Assert> list(int aid) {
		String sql = "select * from api_assert where aid = ?";
		Object[] params = {aid};
		List<Assert> beans = new ArrayList<Assert>();
		
		ResultSet rs = super.query(sql, params);
		try {
			while(rs.next()) {
				Assert bean = new Assert();
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setAssertExpress(rs.getString("assert_express"));
				bean.setAssertExpect(rs.getString("assert_expect"));
				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return beans;
	}
	
}
