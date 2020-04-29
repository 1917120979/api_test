package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import api.bean.Project;
import api.util.DateUtil;

/**
 * 
 * @ClassName:  ProjectDAO   
 * @Description:和数据库交互的DAO类  
 * @author: Durant2035
 * @date:   2020年4月15日 下午8:33:28      
 * @Copyright:
 */
public class ProjectDAO extends BaseDAO{
	private UserDAO uDAO = new UserDAO();

	public boolean add(Project bean) {
		String sql = "insert into project_info values(null,?,?,?,?,?,?)";
		Object[] params = {bean.getName(), bean.getSign(), bean.getEncrypt(), bean.getUser().getId(), DateUtil.d2t(new Date()), bean.getDescription()};
		return super.update(sql, params);
	}
	
	public boolean delete(int id) {
		String sql = "delete from project_info where id ="+id;
		return super.update(sql);
	}
	
	public boolean update(Project bean) {
		String sql = "update project_info set name = ? ,sign = ?,encrypt = ?, description=? where id=?";
		Object[] params = {bean.getName(), bean.getSign(), bean.getEncrypt(), bean.getDescription(), bean.getId()};
		return super.update(sql, params);
	}
	
	/**
	 * 
	 * @Title: get   
	 * @Description: 获取指定id的Project对象  
	 * @param: @param id
	 * @param: @return      
	 * @return: Project      
	 * @throws
	 */
	public Project get(int id) {
		String sql = "select * from project_info where id ="+id;
		ResultSet rs = super.query(sql);		
		try {
			while (rs.next()) {
				Project bean = new Project();
				bean.setId(id);
				bean.setName(rs.getString("name"));
				bean.setSign(rs.getInt("sign"));
				bean.setEncrypt(rs.getInt("encrypt"));
				bean.setUser(uDAO .get(rs.getInt("uid")));
				bean.setCreateDate(rs.getTimestamp("create_date").toString());
				bean.setDescription(rs.getString("description"));
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: list   
	 * @Description: 获取指定行数的Project对象列表  
	 * @param: @param start
	 * @param: @param count
	 * @param: @return      
	 * @return: List<Project>      
	 * @throws
	 */
	public List<Project> list() {
		String sql = "select * from project_info";
		List<Project> beans = new ArrayList<Project>();
		
		ResultSet rs = super.query(sql);
		try {
			while (rs.next()) {
				Project bean = new Project();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setSign(rs.getInt("sign"));
				bean.setEncrypt(rs.getInt("encrypt"));
				bean.setUser(uDAO.get(rs.getInt("uid")));
				bean.setCreateDate(rs.getTimestamp("create_date").toString());
				bean.setDescription(rs.getString("description"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.close();
			if (null != rs) {
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
		String sql = "select count(*) from project_info";
		return super.getTotal(sql);
	}
}
