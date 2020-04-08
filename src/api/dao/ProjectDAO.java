package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Project;

public class ProjectDAO extends BaseDao{
	public boolean add(Project bean) {
		String sql = "insert into project values(null,?,?,?)";
		Object[] params = {bean.getName(), bean.getIsSign(), bean.getIsEncript()};
		return super.update(sql, params, Project.class);
	}
	
	public boolean delete(int id) {
		String sql = "delete from project where id = ?";
		Object[] params = {id};
		return super.update(sql, params, Project.class);
	}
	
	public boolean update(Project bean) {
		String sql = "update project set project_name = ? ,isSign = ?,isEncript = ? where id=?";
		Object[] params = {bean.getName(), bean.getIsSign(), bean.getIsEncript(),bean.getId()};
		return super.update(sql, params, Project.class);
	}
	
	public Project get(int id) {
		String sql = "select * from project where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Project bean = new Project();
		try {
			while (rs.next()) {
				bean.setId(id);
				bean.setName(rs.getString("project_name"));
				bean.setIsSign(rs.getInt("isSign"));
				bean.setIsEncript(rs.getInt("isEncript"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return bean;
	}
	
	public List<Project> list(int start, int count) {
		String sql = "select * from project limit ?,?";
		Object[] params = {start, count};
		List<Project> beans = new ArrayList<Project>();
		
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				Project bean = new Project();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("project_name"));
				bean.setIsSign(rs.getInt("isSign"));
				bean.setIsEncript(rs.getInt("isEncript"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return beans;
	}
	
	public int getTotal() {
		String sql = "select count(*) from project";
		return super.getTotal(sql, null);
	}
}
