package api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;

import api.util.DBUtil;

public class BaseDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet query(String sql, Object[] params) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			if (null != params && params.length > 0 ) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}

			rs = pst.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet query(String sql) {
		return query(sql, null);
	}
	
	public Boolean update(String sql, Object[] params) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (null != params && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			pst.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			close();
		}
	}
	
	public Boolean update(String sql) {
		return update(sql, null);
	}
	
	public ResultSet insert(String sql, Object[] params) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (null != params && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			pst.execute();
			rs = pst.getGeneratedKeys();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;	
	}
	
	public int getTotal(String sql, Object[] params) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if ( null != params && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
	
	public int getTotal(String sql) {
		return getTotal(sql, null);
	}
}
