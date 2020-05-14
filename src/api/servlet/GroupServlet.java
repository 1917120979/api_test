package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.Group;

@SuppressWarnings("serial")
public class GroupServlet extends BaseBackServlet{
	private static final Logger logger = LoggerFactory.getLogger(GroupServlet.class);
	
	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Group bean = new Group();
		bean.setProject(pDAO.get(pid));
		bean.setName(request.getParameter("name"));		
		logger.debug("要新增的对象是>>>"+bean.toString());
		
		JSONObject json = new JSONObject();		
		if (gDAO.add(bean)) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", "null");
		}else {
			json.put("msg", "fail");
			json.put("code", 401);
			json.put("data", "null");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		JSONObject json = new JSONObject();		
		if (gDAO.delete(id) && apiDAO.delete(id)) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", "null");
		}else {
			json.put("msg", "fail");
			json.put("code", 401);
			json.put("data", "null");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Group g = gDAO.get(id);
		
		JSONObject json = new JSONObject();		
		if (g != null) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", g);
		}else {
			json.put("msg", "fail");
			json.put("code", 401);
			json.put("data", "null");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		Group bean = new Group();
		bean.setId(id);
		bean.setProject(pDAO.get(pid));
		bean.setName(request.getParameter("name"));
		
		JSONObject json = new JSONObject();		
		if (gDAO.update(bean)) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", "null");
		}else {
			json.put("msg", "fail");
			json.put("code", 401);
			json.put("data", "null");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}
	
	/**
	 * 测试接口，请求path admin_group_list
	 */
	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
