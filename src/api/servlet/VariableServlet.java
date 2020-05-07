package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Variable;

@SuppressWarnings("serial")
public class VariableServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {	
		return null;
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int type = Integer.parseInt(request.getParameter("type"));
	
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String comments =request.getParameter("comments");
		
		Variable bean = new Variable();
		bean.setProject(pDAO.get(pid));
		bean.setType(type);
		bean.setName(name);
		bean.setValue(value);
		bean.setComments(comments);
		
		JSONObject json = new JSONObject();
		
		if (vDAO.add(bean)) {
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
		if (vDAO.delete(id)) {
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
		Variable v = vDAO.get(id);
		
		JSONObject json = new JSONObject();
		if (v != null) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", v);
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
		int type = Integer.parseInt(request.getParameter("type"));
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String comments =request.getParameter("comments");
		
		Variable bean = new Variable();
		bean.setId(id);
		bean.setName(name);
		bean.setValue(value);
		bean.setType(type);
		bean.setComments(comments);
		
		JSONObject json = new JSONObject();
		
		if (vDAO.update(bean)) {
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

}
