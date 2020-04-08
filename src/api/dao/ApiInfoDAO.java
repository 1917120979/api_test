package api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import api.bean.ApiInfo;
import api.util.DBUtil;

public class ApiInfoDAO extends BaseDao{
	public void add(ApiInfo bean) throws Exception {
		String sql = "insert into api_info values(null,?,null,?,?,?,?,?,?,?)";
		Object[]  params= {bean.getProject().getId(), bean.getApiName(),bean.getProtocol(),bean.getServerName(),bean.getPortNum(),bean.getMethod(),bean.getPath(),bean.getDataType()};
		super.update(sql, params, ApiInfo.class);
	}
	
	public void delete(int id) throws Exception {
		String sql = "delete from api_info where id = ?";
		Object[]  params= {id};
		super.update(sql, params, ApiInfo.class);
	}
	
	public void update(ApiInfo bean) throws Exception {
		String sql = "update api_info set apiName = ?,protocol = ?,serverName = ?,portNum = ?,method=?,path=?, dataType = ? where id = ?";
		Object[]  params= {bean.getApiName(),bean.getProtocol(),bean.getServerName(),bean.getPortNum(),bean.getMethod(),bean.getPath(),bean.getDataType(),bean.getId()};
		super.update(sql, params, ApiInfo.class);
	}
	
	public List<ApiInfo> list(int pid, int start, int count) {
		String sql = "select * from api_info where pid = ? limit ? , ?";
		Object[] params = {pid, start, count};
		ResultSet rs = super.query(sql, params);
		List<ApiInfo> beans = new ArrayList<ApiInfo>();
		try {
			while(rs.next()) {
				ApiInfo bean = new ApiInfo();
				ProjectDAO projectDAO = new ProjectDAO();
				
				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(pid));
				bean.setApiName(rs.getString("api_name"));
				bean.setProtocol(rs.getString("protocol"));
				bean.setServerName(rs.getString("server_name"));
				bean.setPortNum(rs.getString("port_num"));
				bean.setMethod(rs.getString("method"));
				bean.setPath(rs.getString("path"));
				bean.setDataType(rs.getString("data_type"));
				bean.setIsRelation(rs.getInt("isRelation"));
				
				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				
				bean.setId(id);
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setApiName(rs.getString("api_name"));
				bean.setProtocol(rs.getString("protocol"));
				bean.setServerName(rs.getString("server_name"));
				bean.setPortNum(rs.getString("port_num"));
				bean.setMethod(rs.getString("method"));
				bean.setPath(rs.getString("path"));
				bean.setDataType(rs.getString("data_type"));
				bean.setIsRelation(rs.getInt("isRelation"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;	
	}
}
