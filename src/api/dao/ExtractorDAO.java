package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Extractor;

public class ExtractorDAO extends BaseDao{
	public boolean add(Extractor bean) {
		String sql = "insert into api_extractor values(null,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(), bean.getName(), bean.getExpression()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_extractor where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(Extractor bean) {
		String sql = "update api_extractor set name = ? ,expression = ? where id = ?";
		Object[] params = {bean.getName(), bean.getExpression(),bean.getId()};
		return super.update(sql, params);
	}
	
	public Extractor get(int id) {
		String sql = "select * from api_extractor where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Extractor bean = new Extractor();
		try {
			while(rs.next()) {
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				bean.setId(id);
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setName(rs.getString("name"));
				bean.setExpression(rs.getString("expression"));
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
		return bean;
	}
	
	public List<Extractor> list(int aid) {
		String sql = "select * from api_extractor where aid = ?";
		Object[] params = {aid};
		List<Extractor> beans = new ArrayList<Extractor>();
		
		ResultSet rs = super.query(sql, params);
		try {
			while(rs.next()) {
				Extractor bean = new Extractor();
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setName(rs.getString("name"));
				bean.setExpression(rs.getString("expression"));
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
