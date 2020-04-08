package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.Project;
import api.bean.ProjectVariable;
import api.util.Page;

public class ProjectVariableServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {	
		int pid = Integer.parseInt(request.getParameter("pid"));
		String type = request.getParameter("type");
		List<ProjectVariable> pvs = null;
		
		if (type != null) {
			if (type.equals("1")) {
				pvs = pvDAO.list(pid, 0, page.getStart(), page.getCount());
			}	
			if (type.equals("2")) {
				pvs = pvDAO.list(pid, -1, page.getStart(), page.getCount());
			}
			if (type.equals("3")) {
				pvs = pvDAO.list(pid, page.getStart(), page.getCount());
			}
		}else {
			type = "0";
			pvs = pvDAO.listAll(pid, page.getStart(), page.getCount());
		}
		
		Project p = pDAO.get(pid);
		int total = pvDAO.getTotal(pid);
		page.setTotal(total);
		
		request.setAttribute("type", type);
		request.setAttribute("p", p);
		request.setAttribute("page", page);
		request.setAttribute("pvs", pvs);

		return "admin/listProjectVariable.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		ApiInfo apiInfo = new ApiInfo();
		apiInfo.setId(aid);
		
		String variableName = request.getParameter("variableName");
		String variableValue = request.getParameter("variableValue");
		
		ProjectVariable bean = new ProjectVariable();
		bean.setProject(pDAO.get(pid));
		bean.setApiInfo(apiInfo);
		bean.setVariableName(variableName);
		bean.setVariableValue(variableValue);
		
		JSONObject json = new JSONObject();
		
		if (pvDAO.isExistVariableName(bean.getProject().getId(), bean.getVariableName()) < 0) {
			
			if (pvDAO.add(bean)) {
				json.put("msg", "sucess");
				json.put("code", 0);
				json.put("data", "null");
			}else {
				json.put("msg", "fail");
				json.put("code", 401);
				json.put("data", "null");
			}
		}else {			
			json.put("msg", "name repeat");
			json.put("code", 402);
			json.put("data", "null");		
		}
		
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		JSONObject json = new JSONObject();
		if (pvDAO.delete(id)) {
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
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ProjectVariable pv = pvDAO.get(id);
		
		JSONObject json = new JSONObject();
		if (pv != null) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", pv);
		}else {
			json.put("msg", "fail");
			json.put("code", 401);
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));		
		String variableName = request.getParameter("variableName");
		String variableValue = request.getParameter("variableValue");
		
		ProjectVariable bean = new ProjectVariable();
		bean.setId(id);
		bean.setVariableName(variableName);
		bean.setVariableValue(variableValue);
		
		JSONObject json = new JSONObject();
		
		if (pvDAO.add(bean)) {
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
