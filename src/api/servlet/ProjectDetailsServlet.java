package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Project;
import api.bean.Variable;

@SuppressWarnings("serial")
public class ProjectDetailsServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {	
		int pid = Integer.parseInt(request.getParameter("pid"));
		Project p = pDAO.get(pid);
		List<Variable> uvs = vDAO.list(pid, 1);
		List<Variable> hvs = vDAO.list(pid, 2);
		List<Variable> pvs = vDAO.list(pid, 3);
		List<Variable> avs = vDAO.list(pid, 4);
		
		request.setAttribute("p", p);
		request.setAttribute("uvs", uvs);
		request.setAttribute("hvs", hvs);
		request.setAttribute("pvs", pvs);
		request.setAttribute("avs", avs);

		return "pages/projectDetails.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int type = Integer.parseInt(request.getParameter("type"));
	
		String variableName = request.getParameter("variableName");
		String variableValue = request.getParameter("variableValue");
		
		Variable bean = new Variable();
		bean.setProject(pDAO.get(pid));
		bean.setType(type);
		bean.setName(variableName);
		bean.setValue(variableValue);
		
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
		String variableName = request.getParameter("variableName");
		String variableValue = request.getParameter("variableValue");
		
		Variable bean = new Variable();
		bean.setId(id);
		bean.setName(variableName);
		bean.setValue(variableValue);
		bean.setType(type);
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
