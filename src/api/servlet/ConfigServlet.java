package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Config;
import api.bean.Project;
import api.bean.Variable;
import api.util.Page;

public class ConfigServlet extends BaseBackServlet{



	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		return null;
	}

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String configName = request.getParameter("configName");
		String configValue = request.getParameter("configValue");
		
		Config bean = new Config();
		bean.setProject(projectDAO.get(pid));
		bean.setConfigName(configName);
		bean.setConfigValue(configValue);
		String redirect = "@admin_projectDetail_list?pid="+pid;
		try {
			configDAO.add(bean);
		} catch (Exception e) {
			redirect = redirect + "&msg=add fail";
		}
		
		return redirect;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String redirect = "@admin_projectDetail_list?pid="+pid;
		try {
			configDAO.delete(id);
		} catch (Exception e) {
			redirect = redirect + "&msg=delete fail";
		}
		
		return redirect;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Config bean = configDAO.get(id);
		JSONObject json = new JSONObject();
		json.put("config", bean);
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String configName = request.getParameter("configName");
		String configValue = request.getParameter("configValue");
		
		Config bean = new Config();
		bean.setId(id);
		bean.setProject(projectDAO.get(pid));
		bean.setConfigName(configName);
		bean.setConfigValue(configValue);
		
		String redirect = "@admin_projectDetail_list?pid="+pid;
		try {
			configDAO.update(bean);
		} catch (Exception e) {
			redirect = redirect + "&msg=update fail";
		}	
		return redirect;
	}

}
