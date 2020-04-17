package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql = "insert into api_debug_result values(null,?,?,?,?,?,?)";
		Object[] params = {bean.getApiInfo().getId(), bean.getDebugReq(),bean.getDebugResp(),bean.getDebugExtractor(),bean.getDebugAssert(),DateUtil.d2t(bean.getDate())};
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
		String sql = "delete from api_debug_result where aid = ?";
		Object[] params = {aid};
		return super.update(sql, params);
	}
	
	public List<DebugResult> list(int aid){
		String sql = "select * from api_debug_result where aid = ?";
		Object[] params = {aid};
		List<DebugResult> beans = new ArrayList<DebugResult>();
		ResultSet rs = super.query(sql, params);
		try {
			while(rs.next()) {
				DebugResult bean = new DebugResult();
				ApiInfoDAO apiInfoDAO =  new ApiInfoDAO();
				bean.setId(rs.getInt("id"));
				bean.setApiInfo(apiInfoDAO.get(aid));
				bean.setDebugReq(rs.getString("debug_request"));
				bean.setDebugResp(rs.getString("debug_response"));
				bean.setDebugExtractor(rs.getString("debug_extractor"));
				bean.setDebugAssert(rs.getString("debug_assert"));
				bean.setDate(DateUtil.t2d(rs.getTimestamp("debug_time")));
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
