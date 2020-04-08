package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Config;

public class ConfigDAO extends BaseDao{
	public void add(Config bean) throws Exception {
		String sql = "insert into project_config values(null,?,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getConfigName(), bean.getConfigValue()};
		super.update(sql, params, Config.class);
	}
	
	public void update(Config bean) throws Exception {
		String sql = "update project_config set conf_name = ? ,conf_value = ? where id= ?";
		Object[] params = {bean.getConfigName(), bean.getConfigValue(), bean.getId()};
		super.update(sql, params, null);
	}
	
	public void delete(int id) throws Exception {
		String sql = "delete from project_config where id= ?";
		Object[] params = {id};
		super.update(sql, params, null);
	}
	
	public Config get(int id) {
		String sql = "select * from project_config where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Config bean = new Config();
		try {
			while(rs.next()) {
				bean.setId(id);
				ProjectDAO projectDAO = new ProjectDAO();
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setConfigName(rs.getString("conf_name"));
				bean.setConfigValue(rs.getString("conf_value"));
			}
		} catch (SQLException e) {
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
	
	public List<Config> list(int pid, int start, int count){
		String sql = "select * from project_config where pid = ? limit ?,?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<Config> beans = new ArrayList<Config>();
		try {
			while(rs.next()) {
				Config bean = new Config();
				ProjectDAO projectDAO = new ProjectDAO();
				
				bean.setId(rs.getInt("id"));			
				bean.setProject(projectDAO.get(pid));
				bean.setConfigName(rs.getString("conf_name"));
				bean.setConfigValue(rs.getString("conf_value"));
				
				beans.add(bean);
			}
		} catch (SQLException e) {
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
	
	public List<Config> list(int pid){
		return list(pid, 0, Short.MAX_VALUE);
	}
	
	public int getTotal(int pid) {
		String sql = "select count(*) from project_config where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}
}
