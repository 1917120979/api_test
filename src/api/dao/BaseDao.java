package api.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Statement;

import api.util.DBUtil;
import api.util.DateUtil;

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

	public <T> List<T> query(String sql, Object[] params, Class<T> clz) {
		List<T> list = new ArrayList<T>();
		try {

			T t = null;
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			if (params.length > 0 && params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}

			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			while (rs.next()) {
				t = clz.newInstance();
				for (int i = 0; i < colNum; i++) {
					String colName = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(colName);
					if (rsmd.getColumnTypeName(i+1).equals("datetime")) {
						value = DateUtil.t2d((Timestamp) value);
					}
					if (value == null) {
						continue;
					}
					Field field = clz.getDeclaredField(colName);
					field.setAccessible(true);
					field.set(t, value);
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public <T> Map<String, String> queryMap(String sql, Object[] params){
		Map<String, String> map = new HashMap<String, String>();
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			if (params.length > 0 && params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}

			rs = pst.executeQuery();
			while (rs.next()) {
				String key = rs.getString("propName");
				String value = rs.getString("propValue");
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return map;
	}

	public <T> String queryJson(String sql, Object[] params){
		JSONObject json = new JSONObject();
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			if (params.length > 0 && params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}

			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < colNum; i++) {
					String colName = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(colName);
					json.put(colName, (String) value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return json.toJSONString();
	}
	
	public <T> void update(String sql, Object[] params, Class<T> clz) {
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (params.length > 0 && params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			pst.execute();
			if (sql.contains("insert")) {
				rs = pst.getGeneratedKeys();
				while (rs.next()) {
					T t = clz.newInstance();
					Field field = clz.getDeclaredField("id");
					field.setAccessible(true);
					field.set(t, rs.getInt(1));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
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
