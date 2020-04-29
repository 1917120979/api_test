package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.RegularExtractor;

public class RegularExtractorDAO extends BaseDAO{
	public boolean add(RegularExtractor bean) {
		String sql = "insert into api_extractor values(null,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(), bean.getExtractorName(), bean.getRegularExpression()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_extractor where id = ?" + id;
		return super.update(sql);
	}
	
	public boolean update(RegularExtractor bean) {
		String sql = "update api_extractor set extractor_name = ? ,extractor_expression = ? where id = ?";
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
	public RegularExtractor get(int id) {
		String sql = "select * from api_extractor where id = "+id;
		ResultSet rs = super.query(sql, null);		
		try {
			while(rs.next()) {
				RegularExtractor bean = new RegularExtractor();
				ApiDAO apiInfoDAO = new ApiDAO();
				
				bean.setId(id);
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setExtractorName(rs.getString("extractor_name"));
				bean.setRegularExpression(rs.getString("extractor_expression"));				
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
	public List<RegularExtractor> list(int aid) {
		String sql = "select * from api_extractor where aid = "+aid;
		List<RegularExtractor> beans = new ArrayList<RegularExtractor>();		
		ResultSet rs = super.query(sql);
		try {
			while(rs.next()) {
				RegularExtractor bean = new RegularExtractor();
				ApiDAO apiInfoDAO = new ApiDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
				bean.setExtractorName(rs.getString("extractor_name"));
				bean.setRegularExpression(rs.getString("extractor_expression"));
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
	
	public int getTotal(int aid) {
		String sql = "select count(*) from api_extractor where aid = "+aid;
		return super.getTotal(sql);
		
	}
}
