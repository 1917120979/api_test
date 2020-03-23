package api.dao;

import java.util.Date;
import java.util.List;

import api.bean.ApiResult;
import api.util.DateUtil;

public class ApiResultDAO extends BaseDao{

	public void add(ApiResult bean) {
		String sql = "insert into api_result values(null,?,?,?,?)";
		Object[] params = {bean.getApiId(),bean.getRequest(),bean.getResponse(),DateUtil.d2t(new Date())};
		super.update(sql, params, ApiResult.class);
	}
	
	public void delete(int id) {
		String sql = "delete from api_result where id = ?";
		Object[] params = {id};
		super.update(sql, params, ApiResult.class);
	}
	
	public void deleteAll(int apiId) {
		String sql = "delete from api_result where apiId = ?";
		Object[] params = {apiId};
		super.update(sql, params, ApiResult.class);
	}
	
	public List<ApiResult> list(int apiId, int start, int count){
		String sql = "select * from api_result where apiId = ? limit ? ,?";
		Object[] params = {apiId, start, count};
		return  super.query(sql, params, ApiResult.class);
	}
	
	public List<ApiResult> list(int apiId){
		return list(apiId, 0, Short.MAX_VALUE);
	}
	
	public int getTotal(int apiId) {
		String sql = "select count(*) from api_result where apiId = ?";
		Object[] params = {apiId};	
		return super.getTotal(sql, params);
	}

	public ApiResult get(int id) {
		String sql = "select * from api_result where id= ?";
		Object[] params = {id};
		return  super.query(sql, params, ApiResult.class).get(0);
	}
}
