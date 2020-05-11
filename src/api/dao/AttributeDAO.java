package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import api.bean.Attribute;
import api.bean.Variable;

public class AttributeDAO extends BaseDAO {
	private VariableDAO pvDAO = new VariableDAO();

	public boolean add(Attribute bean) {
		if (null != bean.getTestcase()) {
			String sql = "insert into api_attribute values(null,null,?,?,?,?,?)";
			Object[] params = {bean.getTestcase().getId(), bean.getName(), bean.getValue(),
					bean.getType(), bean.getComments() };
			return super.update(sql, params);
		}else {
			String sql = "insert into api_attribute values(null,?,null,?,?,?,?)";
			Object[] params = {bean.getApi().getId(), bean.getName(), bean.getValue(),
					bean.getType(), bean.getComments() };
			return super.update(sql, params);
		}
	}

	public boolean delete(int id) {
		String sql = "delete from api_attribute where id = ?";
		Object[] params = { id };
		return super.update(sql, params);
	}

	public boolean update(Attribute bean) {
		String sql = "update api_attribute set name= ?,value =?,type=?,comments=? where id = ?";
		Object[] params = { bean.getName(), bean.getValue(), bean.getType(), bean.getComments(), bean.getId() };
		return super.update(sql, params);
	}

	public List<Attribute> list(int aid,int tid, int type) {
		List<Attribute> beans = new ArrayList<Attribute>();
		ResultSet rs = null;
		if (aid == 0) {
			String sql = "select * from api_attribute where tid = ? and type = ?";
			Object[] params = { tid, type};
			rs = super.query(sql, params);
		}
		if (tid == 0) {
			String sql = "select * from api_attribute where aid = ? and type = ?";
			Object[] params = { aid, type };
			rs = super.query(sql, params);
		}		
		try {
			while (rs.next()) {
				Attribute bean = new Attribute();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setValue(rs.getString("value"));
				bean.setType(rs.getInt("type"));
				bean.setComments(rs.getString("comments"));
				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	public Attribute get(int id) {
		String sql = "select * from api_attribute where id = ?";
		Object[] params = { id };
		ResultSet rs = super.query(sql, params);

		try {
			while (rs.next()) {
				Attribute bean = new Attribute();
				bean.setId(id);
				bean.setName(rs.getString("name"));
				bean.setValue(rs.getString("value"));
				bean.setType(rs.getInt("type"));
				bean.setComments(rs.getString("comments"));
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public Map<String, String> getMap(int aid, int type) {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select name ,value from api_attribute where aid = ? and type = ?";
		Object[] params = { aid, type };
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				String key = rs.getString("name");
				String value = rs.getString("value");
				if (value.startsWith("$")) {
					String name = StringUtils.substringBetween(value, "{", "}");
					value = pvDAO.getValue(name);
				}
				map.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public String getJson(int aid, int type) {
		JSONObject json = new JSONObject();
		String sql = "select name ,value from api_attribute where aid = ? and type = ?";
		Object[] params = { aid, type };
		ResultSet rs = super.query(sql, params);
		try {
			while (rs.next()) {
				String key = rs.getString("name");
				String value = rs.getString("value");
				if (value.startsWith("$")) {
					String name = StringUtils.substringBetween(value, "{", "}");
					value = pvDAO.getValue(name);
				}
				json.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public boolean addPublicVar(Variable pv, int aid) {
		String sql = "insert into api_attribute values(null,?,null,?,?,?,?)";
		Object[] params = { aid, pv.getName(), pv.getValue(), pv.getType(), pv.getComments() };
		return super.update(sql, params);
	}
}
