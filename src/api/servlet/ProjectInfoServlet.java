package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.bean.Project;
import api.bean.Variable;

@SuppressWarnings("serial")
public class ProjectInfoServlet extends BaseBackServlet{



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

		return "pages/projectInfo.jsp";
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	

}
