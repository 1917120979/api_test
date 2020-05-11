package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Extractor;

public class ExtractorDAO extends BaseDAO{
	public boolean add(Extractor bean) {
		if (null != bean.getTestcase()) {
			String sql = "insert into extractor_info values(null,null,?,?,?,?,?)";
			Object[] params = {bean.getTestcase().getId(), bean.getExtractorName(),bean.getVariableName(),
					bean.getRegularExpression(), bean.getComments() };
			return super.update(sql, params);
		}else {
			String sql = "insert into extractor_info values(null,?,null,?,?,?,?)";
			Object[] params = {bean.getApi().getId(), bean.getExtractorName(),bean.getVariableName(),
					bean.getRegularExpression(), bean.getComments() };
			return super.update(sql, params);
		}
	}
	
	public boolean delete(int id) {
		String sql = "delete from extractor_info where id = ?";
		Object[] params = { id };
		return super.update(sql,params);
	}
	
	public boolean update(Extractor bean) {
		String sql = "update extractor_info set extractor_name = ?,variable_name=? ,extractor_expression = ?,comments=? where id = ?";
		Object[] params = {bean.getExtractorName(), bean.getRegularExpression(), bean.getId()};
		return super.update(sql, params);
	}
	
	/**
	 * 
	 * @Title: get   
	 * @Description: 获取指定id的RegularExtractor对象  
	 * @param: @param id
	 * @param: @return      
	 * @return: RegularExtractor      
	 * @throws
	 */
	public Extractor get(int id) {
		String sql = "select * from extractor_info where id = "+id;
		ResultSet rs = super.query(sql, null);		
		try {
			while(rs.next()) {
				Extractor bean = new Extractor();				
				bean.setId(id);
				bean.setVariableName(rs.getString("variable_name"));
				bean.setExtractorName(rs.getString("extractor_name"));
				bean.setRegularExpression(rs.getString("extractor_expression"));
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
	
	/**
	 * 
	 * @Title: list   
	 * @Description: 获取指定接口的所有提取器 
	 * @param: @param aid
	 * @param: @return      
	 * @return: List<RegularExtractor>      
	 * @throws
	 */
	public List<Extractor> list(int aid,int tid) {
		String sql = "";
		if (aid == 0) {
			sql = "select * from extractor_info where tid = "+tid;
		}
		if (tid == 0) {
			sql = "select * from extractor_info where aid = "+aid;
		}
		List<Extractor> beans = new ArrayList<Extractor>();		
		ResultSet rs = super.query(sql);
		try {
			while(rs.next()) {
				Extractor bean = new Extractor();				
				bean.setId(rs.getInt("id"));
				bean.setVariableName(rs.getString("variable_name"));
				bean.setExtractorName(rs.getString("extractor_name"));
				bean.setRegularExpression(rs.getString("extractor_expression"));
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
