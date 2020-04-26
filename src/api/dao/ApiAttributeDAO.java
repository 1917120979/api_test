package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiAttribute;
import api.bean.Variable;

public class ApiAttributeDAO extends BaseDAO{
	public boolean add(ApiAttribute bean) {
		String sql = "insert into api_attribute values(null,?,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(),bean.getAttributeName(),bean.getAttributeValue(),bean.getType()};
		return super.update(sql, params);
	}
	
	public boolean add(Variable bean, int aid) {
		String sql = "insert into api_attribute values(null,?,?,?,?)";	
		Object[] params = {aid,bean.getVariableName(),bean.getVariableValue(),0};
		return super.update(sql, params);
	}
	
	
	public boolean delete(int id) {
		String sql = "delete from api_attribute where id = ?";
		Object[] params = {id};
		return super.update(sql, params);
	}
	
	public boolean update(ApiAttribute bean) {
		String sql = "update api_attribute set attribute_name= ?,attribute_value =?,type=? where id = ?";
		Object[] params = {bean.getAttributeName(),bean.getAttributeValue(), bean.getType(), bean.getId()};
		return super.update(sql, params);
	}
	
	public List<ApiAttribute> list(int aid, int type){
		String sql = "select * from api_attribute where aid = ? and type = ?";
		Object[] params = {aid, type};
		List<ApiAttribute> beans = new ArrayList<ApiAttribute>();
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				ApiAttribute bean = new ApiAttribute();
				bean.setId(rs.getInt("id"));
				bean.setAttributeName(rs.getString("attribute_name"));
				bean.setAttributeValue(rs.getString("attribute_value"));
				bean.setType(rs.getInt("type"));
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
	
	public List<ApiAttribute> list(int aid){
		String sql = "select * from api_attribute where aid= ?";
		Object[] params = {aid};
		List<ApiAttribute> beans = new ArrayList<ApiAttribute>();
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				ApiAttribute bean = new ApiAttribute();
				bean.setId(rs.getInt("id"));
				bean.setAttributeName(rs.getString("attribute_name"));
				bean.setAttributeValue(rs.getString("attribute_value"));
				bean.setType(rs.getInt("type"));
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

	public ApiAttribute get(int id) {
		String sql = "select * from api_attribute where id = ?";
		Object[] params = {id};
		ResultSet rs = super.query(sql, params);
		ApiAttribute bean = new ApiAttribute();
		try {
			while (rs.next()) {
				
				bean.setId(id);
				bean.setAttributeName(rs.getString("attribute_name"));
				bean.setAttributeValue(rs.getString("attribute_value"));
				bean.setType(rs.getInt("type"));
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
	
	public Map<String, String> getHeaderMap(int aid){
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select attribute_name ,attribute_value from api_attribute where aid = ? and type = 0";
		Object[] params = {aid};
		ResultSet rs = super.query(sql, params);	
		try {
			while (rs.next()) {
				String key = rs.getString("attribute_name");
				String value = rs.getString("attribute_value");
				if (value.startsWith("${")) {
					value = StringUtils.substringBetween(value, "{","}");
					VariableDAO pvDAO = new VariableDAO();
					value = pvDAO.getString(value);
				}
				map.put(key, value);
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
		return map;
	}
	
	public String getRequestJson(int aid){
		JSONObject json = new JSONObject();
		String sql = "select attribute_name ,attribute_value from api_attribute where aid = ? and type = 1";
		Object[] params = {aid};
		ResultSet rs = super.query(sql, params);	
		try {
			while (rs.next()) {
				String key = rs.getString("attribute_name");
				String value = rs.getString("attribute_value");
				if (value.startsWith("${")) {
					value = StringUtils.substringBetween(value, "{","}");
					VariableDAO pvDAO = new VariableDAO();
					value = pvDAO.getString(value);
				}
				json.put(key, value);
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
		return json.toJSONString();
	}
	
	public Map<String, String> getRequestMap(int aid){
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select attribute_name ,attribute_value from api_attribute where aid = ? and type = 1";
		Object[] params = {aid};
		ResultSet rs = super.query(sql, params);	
		try {
			while (rs.next()) {
				String key = rs.getString("attribute_name");
				String value = rs.getString("attribute_value");
				map.put(key, value);
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
		return map;
	}
}
