package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Project;
import api.util.Page;

public class VariableServlet extends BaseBackServlet{

	

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String redirect = "@admin_projectDetail_list?pid="+pid;
		try {
			variableDAO.deleteAll(pid);
		} catch (Exception e) {
			redirect = redirect + "?msg=delete fail";
		}
		return redirect;
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
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

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

	

}
