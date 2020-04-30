package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Api;
import api.bean.Group;

public class ApiDAO extends BaseDAO{
	/**
	 * 
	 * @Title: add   
	 * @Description: 添加接口并设置接口id  
	 * @param: @param bean
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(Api bean) {
		String sql = "insert into api_info values(null,?,?,?,?,?,?,?,?)";
		Object[]  params= {bean.getProject().getId(), bean.getGroup().getId(),bean.getName(),bean.getUrl(),bean.getMethod(),bean.getDataType(),bean.getHasExtractor(),bean.getHasAssert()};	
		ResultSet rs = super.insert(sql, params);
		try {
			while(rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		return true;
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_info where id = ?";
		Object[]  params= {id};
		return super.update(sql, params);
	}
	
	public boolean deleteAll(int gid) {
		String sql = "delete from api_info where gid = ?";
		Object[]  params= {gid};
		return super.update(sql, params);
	}
	
	public boolean update(Api bean) {
		String sql = "update api_info set api_name = ?,url = ?,method=?, data_type =? where id = ?";
		Object[]  params= {bean.getName(),bean.getUrl(),bean.getMethod(),bean.getDataType(),bean.getId()};
		return super.update(sql, params);
	}
	
	public boolean updateFlag(Api bean) {
		String sql = "update api_info set has_extractor=? ,has_assert= ? where id = ?";
		Object[]  params= {bean.getHasExtractor(),bean.getHasAssert(),bean.getId()};
		return super.update(sql, params);
	}
	
	public List<Api> list(int pid, int start, int count) {
		String sql = "select * from api_info where pid = ? and gid = 0 limit ? , ?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<Api> beans = new ArrayList<Api>();
		try {
			while(rs.next()) {
				Api bean = new Api();
				ProjectDAO projectDAO = new ProjectDAO();
				Group group = new Group();
				group.setId(0);
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(pid));
				bean.setGroup(group);
				bean.setName(rs.getString("api_name"));
				bean.setUrl(rs.getString("url"));			
				bean.setMethod(rs.getString("method"));
				bean.setDataType(rs.getInt("data_type"));
				bean.setHasExtractor(rs.getInt("has_extractor"));
				bean.setHasAssert(rs.getInt("has_assert"));
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
	
	public List<Api> list(int gid) {
		String sql = "select * from api_info where gid = ?";
		Object[] params = {gid};
		ResultSet rs = super.query(sql, params);
		List<Api> beans = new ArrayList<Api>();
		try {
			while(rs.next()) {
				Api bean = new Api();
				GroupDAO groupDAO = new GroupDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setGroup(groupDAO.get(gid));
				bean.setName(rs.getString("api_name"));
				bean.setUrl(rs.getString("url"));			
				bean.setMethod(rs.getString("method"));
				bean.setDataType(rs.getInt("data_type"));
				bean.setHasExtractor(rs.getInt("has_extractor"));
				bean.setHasAssert(rs.getInt("has_assert"));
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
	
	public int getTotal() {
		String sql = "select count(*) from api_info";
		return super.getTotal(sql);
	}

	public Api get(int id) {
		String sql = "select * from api_info where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		Api bean = new Api();
		try {
			while(rs.next()) {
				
				ProjectDAO projectDAO = new ProjectDAO();
				GroupDAO groupDAO = new GroupDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setGroup(groupDAO.get(rs.getInt("gid")));
				bean.setName(rs.getString("api_name"));
				bean.setUrl(rs.getString("url"));			
				bean.setMethod(rs.getString("method"));
				bean.setDataType(rs.getInt("data_type"));
				bean.setHasExtractor(rs.getInt("has_extractor"));
				bean.setHasAssert(rs.getInt("has_assert"));
				
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
		return bean;	
	}
}
