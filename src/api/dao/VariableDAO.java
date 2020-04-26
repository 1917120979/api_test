package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Variable;

/**
 * 
 * @ClassName:  VariableDAO   
 * @Description:TODO(描述这个类的作用)   
 * @author: liuyang
 * @date:   2020年4月23日 下午10:27:30      
 * @Copyright:
 */
public class VariableDAO extends BaseDAO{
	
	private ProjectDAO pDAO = new ProjectDAO();

	public boolean add(Variable bean) {	
		String sql = "insert into variable values(null,?,?,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getName(), bean.getValue(),bean.getType()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from variable where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(Variable bean) {
		String sql = "update variable set name = ? ,value = ?,type = ? where id=?";
		Object[] params = {bean.getName(), bean.getValue(), bean.getId()};
		return super.update(sql, params);
	}
	
	public Variable get(int id) {
		String sql = "select * from variable where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Variable bean = new Variable();
		try {
			while (rs.next()) {				
				bean.setId(id);
				bean.setProject(pDAO .get(rs.getInt("pid")));
				bean.setName(rs.getString("name"));
				bean.setValue(rs.getString("value"));
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
	
	public String getValue(String key) {
		String sql = "select value from variable where name = ?";
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
	
	public List<Variable> listAll(int pid){
		String sql = "select * from variable where pid = ?";
		Object[] params = {pid};
		ResultSet rs = super.query(sql, params);
		List<Variable> beans = new ArrayList<Variable>();
		try {
			while(rs.next()) {
				Variable bean = new Variable();
			
				bean.setId(rs.getInt("id"));
				bean.setProject(pDAO.get(rs.getInt("pid")));
				bean.setType(rs.getInt("type"));
				bean.setName(rs.getString("name"));
				bean.setValue(rs.getString("value"));
				
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
	
	public List<Variable> list(int pid, int type){
		String sql = "select * from variable where pid = ? and type = ?";
		Object[] params = {pid, type};
		ResultSet rs = super.query(sql, params);
		List<Variable> beans = new ArrayList<Variable>();
		try {
			while(rs.next()) {
				Variable bean = new Variable();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(pDAO.get(rs.getInt("pid")));
				bean.setType(rs.getInt("type"));
				bean.setName(rs.getString("name"));
				bean.setValue(rs.getString("value"));
				
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
}
