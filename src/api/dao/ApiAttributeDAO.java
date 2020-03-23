package api.dao;

import java.util.List;
import java.util.Map;

import api.bean.ApiAttribute;

public class ApiAttributeDAO extends BaseDao{
	public void add(ApiAttribute bean) {
		String sql = "insert into api_attribute values(null,?,?,?,?)";
		Object[] params = {bean.getApiId(), bean.getPropName(), bean.getPropValue(), bean.getType()};
		super.update(sql, params, ApiAttribute.class);
	}
	
	public void delete(int id) {
		String sql = "delete from api_attribute where id = ?";
		Object[] params = {id};
		super.update(sql, params, ApiAttribute.class);
	}
	
	public void update(ApiAttribute bean) {
		String sql = "update api_attribute set propName=?,propValue=?,type=? where id = ?";
		Object[] params = {bean.getPropName(), bean.getPropValue(), bean.getType(), bean.getId()};
		super.update(sql, params, ApiAttribute.class);
	}
	
	public List<ApiAttribute> list(int apiId, int type, int start, int count){
		String sql = "select * from api_attribute where apiId = ? and type = ? limit ?,?";
		Object[] params = {apiId, type, start, count};
		return super.query(sql, params, ApiAttribute.class);
	}

	public ApiAttribute get(int id) {
		String sql = "select * from api_attribute where id = ?";
		Object[] params = {id};
		return super.query(sql, params, ApiAttribute.class).get(0);
	}
	
	public Map<String, String> getHeaderMap(int apiId){
		String sql = "select propName ,propValue from api_attribute where apiId = ? and type = 0";
		Object[] params = {apiId};
		return queryMap(sql, params);
	}
	
	public String getRequestJson(int apiId){
		String sql = "select propName ,propValue from api_attribute where apiId = ? and type = 1";
		Object[] params = {apiId};
		return queryJson(sql, params);
	}
	
	public Map<String, String> getRequestMap(int apiId){
		String sql = "select propName ,propValue from api_attribute where apiId = ? and type = 1";
		Object[] params = {apiId};
		return queryMap(sql, params);
	}
}
