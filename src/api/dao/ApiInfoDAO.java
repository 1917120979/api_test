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
	public void add(ApiInfo bean) {
		String sql = "insert into api_info values(null,?,null,?,?,?,?,?,?,?)";
		Object[]  params= {bean.getPid(), bean.getApiName(),bean.getProtocol(),bean.getServerName(),bean.getPortNum(),bean.getMethod(),bean.getPath(),bean.getDataType()};
		super.update(sql, params, ApiInfo.class);
	}
	
	public void delete(int id) {
		String sql = "delete from api_info where id = ?";
		Object[]  params= {id};
		super.update(sql, params, ApiInfo.class);
	}
	
	public void update(ApiInfo bean) {
		String sql = "update api_info set apiName = ?,protocol = ?,serverName = ?,portNum = ?,method=?,path=?, dataType = ? where id = ?";
		Object[]  params= {bean.getApiName(),bean.getProtocol(),bean.getServerName(),bean.getPortNum(),bean.getMethod(),bean.getPath(),bean.getDataType(),bean.getId()};
		super.update(sql, params, ApiInfo.class);
	}
	
	public List<ApiInfo> list(int pid, int start, int count) {
		String sql = "select * from api_info where pid = ? limit ? , ?";
		Object[] params = {pid, start, count};
		return super.query(sql, params, ApiInfo.class);		
	}
	
	public int getTotal(int pid) {
		String sql = "select count(*) from api_info where pid = ?";
		Object[] params = {pid};
		return super.getTotal(sql, params);
	}

	public ApiInfo get(int id) {
		String sql = "select * from api_info where id = ?";
		Object[] params = {id};
		return super.query(sql, params, ApiInfo.class).get(0);	
	}
}
