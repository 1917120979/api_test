package api.dao;

import java.util.List;

import api.bean.ApiProject;

public class ApiProectDAO extends BaseDao{
	public void add(ApiProject bean) {
		String sql = "insert into api_project values(null,?)";
		Object[] params = {bean.getProName()};
		super.update(sql, params, ApiProject.class);
	}
	
	public void delete(int id) {
		String sql = "delete from api_project where id = ?";
		Object[] params = {id};
		super.update(sql, params, ApiProject.class);
	}
	
	public void update(ApiProject bean) {
		String sql = "update api_project set proName = ? where id=?";
		Object[] params = {bean.getProName(),bean.getId()};
		super.update(sql, params, ApiProject.class);
	}
	
	public ApiProject get(int id) {
		String sql = "select * from api_project where id = ?";
		Object[] params = {id};
		return super.query(sql, params, ApiProject.class).get(0);
	}
	
	public List<ApiProject> list(int start, int count) {
		String sql = "select * from api_project limit ?,?";
		Object[] params = {start, count};
		return super.query(sql, params, ApiProject.class);
	}
	
	public int getTotal() {
		String sql = "select count(*) from api_project";
		return super.getTotal(sql, null);
	}
}
