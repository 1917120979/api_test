package api.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.ApiProject;
import api.util.Page;

public class ApiInfoServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {	
		ApiInfo aInfo = super.parseClass(request, ApiInfo.class);
		int pid = aInfo.getPid();
		aiDAO.add(aInfo);
		return "@admin_apiInfo_list?pid="+pid;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<ApiInfo> ais = aiDAO.list(pid, page.getStart(), page.getCount());
		int total = aiDAO.getTotal(pid);
		page.setTotal(total);
		
		ApiProject ap = apDAO.get(pid);
		
		request.setAttribute("ap", ap);
		request.setAttribute("ais", ais);
		request.setAttribute("page", page);
		return "admin/listApiInfo.jsp";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		aiDAO.delete(id);
		int pid = aiDAO.get(id).getPid();
		return "@admin_apiInfo_list?pid="+pid;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApiInfo bean = aiDAO.get(id);
		JSONObject json = new JSONObject();
		json.put("api", bean);
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		ApiInfo aInfo = super.parseClass(request, ApiInfo.class);	
		aiDAO.update(aInfo);
		int pid = aInfo.getPid();
		return "@admin_apiInfo_list?pid="+pid;
	}

}
