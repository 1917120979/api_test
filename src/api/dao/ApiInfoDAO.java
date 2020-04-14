package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.ApiInfo;
import api.bean.Group;

public class ApiInfoDAO extends BaseDao{
	/**
	 * 
	 * @Title: add   
	 * @Description: 添加接口并设置接口id  
	 * @param: @param bean
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(ApiInfo bean) {
		String sql = "insert into api_info values(null,?,?,?,?,?,?,?,?)";
		Object[]  params= {bean.getProject().getId(), bean.getGroup().getId(),bean.getApiName(),bean.getUrl(),bean.getMethod(),bean.getDataType(),bean.getHasExtractor(),bean.getHasAssert()};	
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
	
	public boolean update(ApiInfo bean) {
		String sql = "update api_info set api_name = ?,url = ?,method=?, data_type = ? where id = ?";
		Object[]  params= {bean.getApiName(),bean.getUrl(),bean.getMethod(),bean.getDataType(),bean.getId()};
		return super.update(sql, params);
	}
	
	public List<ApiInfo> list(int pid, int start, int count) {
		String sql = "select * from api_info where pid = ? and gid = 0 limit ? , ?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<ApiInfo> beans = new ArrayList<ApiInfo>();
		try {
			while(rs.next()) {
				ApiInfo bean = new ApiInfo();
				ProjectDAO projectDAO = new ProjectDAO();
				Group group = new Group();
				group.setId(0);
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(pid));
				bean.setGroup(group);
				bean.setApiName(rs.getString("api_name"));
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
	
	public List<ApiInfo> list(int gid) {
		String sql = "select * from api_info where gid = ?";
		Object[] params = {gid};
		ResultSet rs = super.query(sql, params);
		List<ApiInfo> beans = new ArrayList<ApiInfo>();
		try {
			while(rs.next()) {
				ApiInfo bean = new ApiInfo();
				GroupDAO groupDAO = new GroupDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setGroup(groupDAO.get(gid));
				bean.setApiName(rs.getString("api_name"));
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
	
	public int getTotal(int pid) {
		String sql = "select count(*) from api_info where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}

	public ApiInfo get(int id) {
		String sql = "select * from api_info where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		ApiInfo bean = new ApiInfo();
		try {
			while(rs.next()) {
				
				ProjectDAO projectDAO = new ProjectDAO();
				GroupDAO groupDAO = new GroupDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setGroup(groupDAO.get(rs.getInt("gid")));
				bean.setApiName(rs.getString("api_name"));
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
