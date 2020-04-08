package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.ApiInfo;
import api.bean.Project;
import api.bean.ProjectVariable;

public class ProjectVariableDAO extends BaseDao{
	
	public boolean add(ProjectVariable bean) {	
		String sql = "insert into project_variable values(null,?,?,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getApiInfo().getId(), bean.getVariableName(), bean.getVariableValue()};
		return super.update(sql, params, Project.class);
	}
	
	public boolean delete(int id) {
		String sql = "delete from project_variable where id = ?";
		Object[] params = {id};
		return super.update(sql, params, Project.class);
	}
	
	public boolean update(ProjectVariable bean) {
		String sql = "update project_variable set variable_name = ? ,variable_value = ? where id=?";
		Object[] params = {bean.getVariableName(), bean.getVariableValue(), bean.getId()};
		return super.update(sql, params, Project.class);
	}
		
	public void deleteAll(int pid) throws Exception {
		String sql = "delete from project_variable where pid= ? and aid > 0";
		Object[] params = {pid};
		super.update(sql, params, null);
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
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				ApiInfo apiInfo = null;
				int aid = rs.getInt("aid");
				if (0 == aid) {
					apiInfo = new ApiInfo();
					apiInfo.setId(0);
				}else {
					apiInfo = apiInfoDAO.get(aid);
				}
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setApiInfo(apiInfo);
				bean.setVariableName(rs.getString("variable_name"));
				bean.setVariableValue(rs.getString("variable_value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return bean;
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
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				ApiInfo apiInfo = null;
				int aid = rs.getInt("aid");
				if (aid > 0) {
					apiInfo = apiInfoDAO.get(aid);
				}else {
					apiInfo = new ApiInfo();
					apiInfo.setId(aid);
				}
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setApiInfo(apiInfo);
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
	
	public List<ProjectVariable> list(int pid, int start, int count){
		String sql = "select * from project_variable where pid = ? and aid > 0 limit ?,?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<ProjectVariable> beans = new ArrayList<ProjectVariable>();
		try {
			while(rs.next()) {
				ProjectVariable bean = new ProjectVariable();
				ProjectDAO projectDAO = new ProjectDAO();
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("aid")));
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
	
	public List<ProjectVariable> list(int pid, int aid, int start, int count){
		String sql = "select * from project_variable where pid = ? and aid = ? limit ?,?";
		Object[] params = {pid, aid, start, count};
		ResultSet rs = super.query(sql, params);
		List<ProjectVariable> beans = new ArrayList<ProjectVariable>();
		try {
			while(rs.next()) {
				ProjectVariable bean = new ProjectVariable();
				ProjectDAO projectDAO = new ProjectDAO();
				ApiInfo apiInfo = new ApiInfo();
				apiInfo = new ApiInfo();
				apiInfo.setId(aid);
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setApiInfo(apiInfo);
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
	
	public List<ProjectVariable> list(int pid){
		return list(pid, 0, Short.MAX_VALUE);
	}
	
	public int getTotal(int pid) {
		String sql = "select count(*) from project_variable where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}
}
