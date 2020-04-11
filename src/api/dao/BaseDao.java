package api.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;

import api.util.DBUtil;

public class BaseDao {

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
			if (params.length > 0 && params != null) {
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
	
	public <T> Boolean update(String sql, Object[] params, Class<T> clz) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (params.length > 0 && params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			pst.execute();
			if (sql.contains("insert") && clz != null) {
				rs = pst.getGeneratedKeys();
				while (rs.next()) {
					T t = clz.newInstance();
					Field field = clz.getDeclaredField("id");
					field.setAccessible(true);
					field.set(t, rs.getInt(1));
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
		
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
}
