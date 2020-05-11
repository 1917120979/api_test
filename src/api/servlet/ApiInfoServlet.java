package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.bean.Api;
import api.bean.Attribute;

@SuppressWarnings("serial")
public class ApiInfoServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {	
		int aid = Integer.parseInt(request.getParameter("aid"));
		Api api = apiDAO.get(aid);
		List<Attribute> has = attrDAO.list(aid,0, 2);
		List<Attribute> pas = attrDAO.list(aid,0, 3);
		
		request.setAttribute("api", api);
		request.setAttribute("has", has);
		request.setAttribute("pas", pas);

		return "/pages/apiInfo.jsp";
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
