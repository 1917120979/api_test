package api.dao;

import java.sql.ResultSet;

import api.bean.Extractor;

public class ExtractorDAO extends BaseDao{
	public boolean add(Extractor bean) {
		String sql = "insert into api_extractor values(null,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(), bean.getName(), bean.getExpression()};
		return super.update(sql, params, Extractor.class);
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_extractor where id = ?";
		Object[] params = {id};
		return super.update(sql, params, null);
	}
	
	public boolean update(Extractor bean) {
		String sql = "update api_extractor set name = ? ,expression = ? where id = ?";
		Object[] params = {bean.getName(), bean.getExpression(),bean.getId()};
		return super.update(sql, params, null);
	}
	
	public Extractor get(int id) {
		String sql = "select * from api_extractor where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Extractor bean = new Extractor();
		try {
			while(rs.next()) {
				bean.setId(id);
				bean.setName(rs.getString("name"));
				bean.setExpression(rs.getString("expression"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
