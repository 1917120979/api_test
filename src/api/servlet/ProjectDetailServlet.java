package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Config;
import api.bean.Project;
import api.bean.Variable;
import api.util.Page;

public class ProjectDetailServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		String msg = request.getParameter("msg");
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<Config> confs = configDAO.list(pid);
		List<Variable> variables = variableDAO.list(pid);
		
		System.out.println(variables);
		Project project = projectDAO.get(pid);
		
		request.setAttribute("msg", msg);
		request.setAttribute("project", project);
		request.setAttribute("confs", confs);
		request.setAttribute("variables", variables);
		return "admin/listProjectDetail.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

}
