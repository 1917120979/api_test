package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Variable;

public class VariableDAO extends BaseDao{
	public void add(Variable bean) throws Exception {
		String sql = "insert into user_variable values(null,?,?,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getApiInfo().getId(), bean.getVarName(), bean.getVarValue()};
		super.update(sql, params, Variable.class);
	}
	
	public void update(Variable bean) throws Exception {
		String sql = "update user_variable set var_value = ? where id= ?";
		Object[] params = {bean.getVarValue(), bean.getId()};
		super.update(sql, params, null);
	}
	
	public void delete(int id) throws Exception {
		String sql = "delete from user_variable where id= ?";
		Object[] params = {id};
		super.update(sql, params, null);
	}
	
	public void deleteAll(int pid) throws Exception {
		String sql = "delete from user_variable where pid= ?";
		Object[] params = {pid};
		super.update(sql, params, null);
	}
	
	public Variable get(int pid, String name) {
		String sql = "select * from user_variable where pid = ? and var_name = ?";
		Object[] params = {pid, name};
		ResultSet rs = super.query(sql, params);
		Variable bean = new Variable();
		try {
			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				ProjectDAO projectDAO = new ProjectDAO();
				bean.setProject(projectDAO.get(pid));
				bean.setVarName(rs.getString("var_name"));
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
	
	public List<Variable> list(int pid, int start, int count){
		String sql = "select * from user_variable where pid = ? limit ?,?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<Variable> beans = new ArrayList<Variable>();
		try {
			while(rs.next()) {
				Variable bean = new Variable();
				ProjectDAO projectDAO = new ProjectDAO();
				ApiInfoDAO apiInfoDAO = new ApiInfoDAO();
				
				bean.setId(rs.getInt("id"));			
				bean.setProject(projectDAO.get(pid));
				bean.setApiInfo(apiInfoDAO.get(rs.getInt("apiId")));
				bean.setVarName(rs.getString("var_name"));
				bean.setVarValue(rs.getString("var_value"));
				
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
	
	public List<Variable> list(int pid){
		return list(pid, 0, Short.MAX_VALUE);
	}
	
	public int getTotal(int pid) {
		String sql = "select count(*) from user_variable where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}
}
