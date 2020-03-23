package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiProject;
import api.util.Page;

public class ApiProjectServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		ApiProject bean = super.parseClass(request, ApiProject.class);
		apDAO.add(bean);
		return "@admin_apiProject_list";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		apDAO.delete(id);
		return "@admin_apiProject_list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApiProject ap = apDAO.get(id);
		JSONObject json = new JSONObject();
		json.put("ap", ap);
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		ApiProject bean = super.parseClass(request, ApiProject.class);
		apDAO.update(bean);
		return "@admin_apiProject_list";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<ApiProject> aps = apDAO.list(page.getStart(), page.getCount());
		int total = apDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("page", page);
		request.setAttribute("aps", aps);
		
		return "admin/listApiProject.jsp";
	}

}
