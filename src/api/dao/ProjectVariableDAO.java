package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.ProjectVariable;

public class ProjectVariableDAO extends BaseDAO{
	
	public boolean add(ProjectVariable bean) {	
		String sql = "insert into project_variable values(null,?,?,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getVariableName(), bean.getVariableValue(),bean.getType()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from project_variable where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(ProjectVariable bean) {
		String sql = "update project_variable set variable_name = ? ,variable_value = ? where id=?";
		Object[] params = {bean.getVariableName(), bean.getVariableValue(), bean.getId()};
		return super.update(sql, params);
	}
		
	public boolean deleteAll(int pid) {
		String sql = "delete from project_variable where pid= ? and aid > 0";
		Object[] params = {pid};
		return super.update(sql, params);
	}
	
	public int isExistVariableName(int pid, String name) {
		String sql = "select * from project_variable where pid = ? and variable_name = ?";
		Object[] params = {pid, name};
		ResultSet rs = super.query(sql, params);
		try {
			while(rs.next()) {
				return rs.getInt("id");
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
		return -1;
	}
	
	public ProjectVariable get(int id) {
		String sql = "select * from project_variable where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		ProjectVariable bean = new ProjectVariable();
		try {
			while (rs.next()) {
				ProjectDAO projectDAO = new ProjectDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setVariableName(rs.getString("variable_name"));
				bean.setVariableValue(rs.getString("variable_value"));
				bean.setType(rs.getInt("type"));
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
	
	public String getString(String key) {
		String sql = "select variable_value from project_variable where variable_name = ?";
		Object[] params = {key};
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				return rs.getString(1);
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
		return "";
	}
	
	public List<ProjectVariable> listAll(int pid, int start, int count){
		String sql = "select * from project_variable where pid = ? limit ?,?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<ProjectVariable> beans = new ArrayList<ProjectVariable>();
		try {
			while(rs.next()) {
				ProjectVariable bean = new ProjectVariable();
				ProjectDAO projectDAO = new ProjectDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setType(rs.getInt("type"));
				bean.setVariableName(rs.getString("variable_name"));
				bean.setVariableValue(rs.getString("variable_value"));
				
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
	
	public List<ProjectVariable> list(int pid, int type, int start, int count){
		String sql = "select * from project_variable where pid = ? and type = ? limit ?,?";
		Object[] params = {pid, type, start, count};
		ResultSet rs = super.query(sql, params);
		List<ProjectVariable> beans = new ArrayList<ProjectVariable>();
		try {
			while(rs.next()) {
				ProjectVariable bean = new ProjectVariable();
				ProjectDAO projectDAO = new ProjectDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setType(rs.getInt("type"));
				bean.setVariableName(rs.getString("variable_name"));
				bean.setVariableValue(rs.getString("variable_value"));
				
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
	
	public List<ProjectVariable> list(int pid ,int type){
		return list(pid, type, 0, Short.MAX_VALUE);
	}
	
	public int getTotal(int pid) {
		String sql = "select count(*) from project_variable where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}
}
