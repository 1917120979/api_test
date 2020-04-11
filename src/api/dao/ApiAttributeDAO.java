package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiAttribute;

public class ApiAttributeDAO extends BaseDao{
	public boolean add(ApiAttribute bean) {
		String sql = "insert into api_attribute values(null,?,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(),bean.getAttributeName(),bean.getAttributeValue(),bean.getType()};
		return super.update(sql, params, ApiAttribute.class);
	}
	
	public boolean delete(int id) {
		String sql = "delete from api_attribute where id = ?";
		Object[] params = {id};
		return super.update(sql, params, null);
	}
	
	public boolean update(ApiAttribute bean) {
		String sql = "update api_attribute set attribute_name= ?,attribute_value =?,type=? where id = ?";
		Object[] params = {bean.getAttributeName(),bean.getAttributeValue(), bean.getType(), bean.getId()};
		return super.update(sql, params, null);
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
				map.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
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
				json.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
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
		}
		return map;
	}
}
