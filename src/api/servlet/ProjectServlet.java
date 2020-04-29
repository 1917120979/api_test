package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.Project;

/**
 * 
 * @ClassName:  ProjectServlet   
 * @Description:继承抽象类并实现抽象方法  
 * @author: Durant2035
 * @date:   2020年4月15日 下午8:39:30      
 * @Copyright:
 */
@SuppressWarnings("serial")
public class ProjectServlet extends BaseBackServlet{
	private static final Logger logger = LoggerFactory.getLogger(ProjectServlet.class);

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {		
		String name = request.getParameter("name");
		int sign = Integer.parseInt(request.getParameter("sign"));
		int encrypt = Integer.parseInt(request.getParameter("encrypt"));
		String description = request.getParameter("description");
				
		Project bean = new Project();
		bean.setName(name);
		bean.setSign(sign);
		bean.setEncrypt(encrypt);
		bean.setUser(super.user);
		bean.setDescription(description);
		
		JSONObject json = new JSONObject();		
		if (pDAO.add(bean)) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", bean);
			logger.debug(String.format("添加成功:%s, bean是%s--->>>", json.toJSONString(), bean.toString()));
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
		if (pDAO.delete(id)) {
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
		Project p = pDAO.get(id);
		
		JSONObject json = new JSONObject();
		if (p != null) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", p);
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
		int sign = Integer.parseInt(request.getParameter("sign"));
		int encrypt = Integer.parseInt(request.getParameter("encrypt"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
			
		Project bean = new Project();
		bean.setId(id);
		bean.setName(name);
		bean.setSign(sign);
		bean.setEncrypt(encrypt);
		bean.setDescription(description);
		
		JSONObject json = new JSONObject();		
		if (pDAO.update(bean)) {
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
	public String list(HttpServletRequest request, HttpServletResponse response) {
		List<Project> ps = pDAO.list();

		request.setAttribute("ps", ps);
		return "pages/listProject.jsp";
	}

}
