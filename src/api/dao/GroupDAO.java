package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Group;

public class GroupDAO extends BaseDao{
	public boolean add(Group bean) {
		String sql = "insert into project_group value(null,?,?)";
		Object[] params = {bean.getProject().getId(), bean.getName()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from project_group where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(Group bean) {
		String sql = "update project_group set group_name = ? where id = ?";
		Object[] params = {bean.getName(), bean.getId()};
		return super.update(sql, params);
	}
	
	public Group get(int id) {
		String sql = "select * from project_group where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Group bean = null;
		try {
			while (rs.next()) {
				bean = new Group();
				bean.setId(id);
				bean.setName(rs.getString("group_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return bean;
	}
	
	public List<Group> list(int pid){
		String sql = "select * from project_group where pid = ?";
		Object[] params = {pid};
		ResultSet rs = super.query(sql, params);
		List<Group> beans = new ArrayList<Group>();
		
		try {
			while (rs.next()) {
				Group bean = new Group();
				ApiInfoDAO apiDAO = new ApiInfoDAO();
				int id = rs.getInt("id");
				bean.setId(id);
				bean.setName(rs.getString("group_name"));
				bean.setApiByGroup(apiDAO.list(id));
				beans.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
		}
		return beans;
	}
}
