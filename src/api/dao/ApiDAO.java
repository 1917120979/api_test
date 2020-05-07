package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.bean.Api;

public class ApiDAO extends BaseDAO {
	/**
	 * 
	 * @Title: add @Description: 添加接口并设置接口id @param: @param
	 * bean @param: @return @return: boolean @throws
	 */
	public boolean add(Api bean) {
		String sql = "insert into api_info values(null,?,?,?,?,?,?,?,?)";
		Object[] params = { bean.getProject().getId(), bean.getName(), bean.getProtocol(), bean.getUrl(),
				bean.getMethod(), bean.getDataType(), bean.getFilesUpload(), bean.getComments() };
		ResultSet rs = super.insert(sql, params);
		try {
			while (rs.next()) {
				bean.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		return true;
	}

	public boolean delete(int id) {
		String sql = "delete from api_info where id = ?";
		Object[] params = { id };
		return super.update(sql, params);
	}

	public boolean update(Api bean) {
		String sql = "update api_info set api_name = ?,protocol = ?,url = ?,method=?, data_type =?,files_upload=?,comments=? where id = ?";
		Object[] params = { bean.getName(), bean.getProtocol(), bean.getUrl(), bean.getMethod(), bean.getDataType(),
				bean.getFilesUpload(), bean.getComments(), bean.getId() };
		return super.update(sql, params);
	}

	public List<Api> list(int pid) {
		String sql = "select * from api_info where pid = ?";
		Object[] params = { pid };
		ResultSet rs = super.query(sql, params);
		List<Api> beans = new ArrayList<Api>();
		try {
			while (rs.next()) {
				Api bean = new Api();
				ProjectDAO projectDAO = new ProjectDAO();

				bean.setId(rs.getInt("id"));
				bean.setProject(projectDAO.get(pid));
				bean.setName(rs.getString("api_name"));
				bean.setProtocol(rs.getInt("protocol"));
				bean.setUrl(rs.getString("url"));
				bean.setMethod(rs.getString("method"));
				bean.setDataType(rs.getInt("data_type"));
				bean.setFilesUpload(rs.getInt("files_upload"));
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

	public int getTotal() {
		String sql = "select count(*) from api_info";
		return super.getTotal(sql);
	}

	public Api get(int id) {
		String sql = "select * from api_info where id = ?";
		Object[] params = { id };
		ResultSet rs = super.query(sql, params);
		
		try {
			while (rs.next()) {
				Api bean = new Api();
				ProjectDAO projectDAO = new ProjectDAO();
				bean.setId(id);
				bean.setProject(projectDAO.get(rs.getInt("pid")));
				bean.setName(rs.getString("api_name"));
				bean.setProtocol(rs.getInt("protocol"));
				bean.setUrl(rs.getString("url"));
				bean.setMethod(rs.getString("method"));
				bean.setDataType(rs.getInt("data_type"));
				bean.setFilesUpload(rs.getInt("files_upload"));
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
}
