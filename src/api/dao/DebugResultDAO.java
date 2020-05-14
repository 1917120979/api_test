package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import api.bean.DebugResult;
import api.util.DateUtil;

public class DebugResultDAO extends BaseDAO{
	
	/**
	 * 
	 * @Title: add   
	 * @Description: 添加调试数据到表  
	 * @param: @param bean
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean add(DebugResult bean) {
		String sql = "insert into debug_result values(null,?,?,?,?,?)";
		Object[] params = {bean.getApi().getId(), bean.getDebugRequest(),bean.getDebugRespose(),bean.getDebugPost(),DateUtil.d2t(new Date())};
		return super.update(sql, params);
	}
	
	/**
	 * 
	 * @Title: delete   
	 * @Description: 删除接口所有的调试数据 
	 * @param: @param aid
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean delete(int aid) {
		String sql = "delete from debug_result where aid = ?";
		Object[] params = {aid};
		return super.update(sql, params);
	}
	
	public List<DebugResult> list(int aid){
		String sql = "select * from debug_result where aid = ?";
		Object[] params = {aid};
		List<DebugResult> beans = new ArrayList<DebugResult>();
		ResultSet rs = super.query(sql, params);
		try {
			while(rs.next()) {
				DebugResult bean = new DebugResult();
				bean.setId(rs.getInt("id"));
				bean.setDebugRequest(rs.getString("debug_request"));
				bean.setDebugRespose(rs.getString("debug_response"));
				bean.setDebugPost(rs.getString("debug_post"));
				bean.setDebugTime(rs.getTimestamp("debug_time").toString());
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
}
