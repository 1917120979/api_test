package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.Project;
import api.util.Page;

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
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int isSign = Integer.parseInt(request.getParameter("isSign"));
		int isEncript = Integer.parseInt(request.getParameter("isEncript"));
		String name = request.getParameter("name");
				
		Project bean = new Project();
		bean.setName(name);
		bean.setIsSign(isSign);
		bean.setIsEncript(isEncript);
		logger.debug("新增的对象是>>>"+bean.toString());
		JSONObject json = new JSONObject();		
		if (pDAO.add(bean)) {
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
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
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
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
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
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		int isSign = Integer.parseInt(request.getParameter("isSign"));
		int isEncript = Integer.parseInt(request.getParameter("isEncript"));
		String name = request.getParameter("name");
			
		Project bean = new Project();
		bean.setId(id);
		bean.setName(name);
		bean.setIsSign(isSign);
		bean.setIsEncript(isEncript);
		
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
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Project> ps = pDAO.list(page.getStart(), page.getCount());
		int total = pDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("page", page);
		request.setAttribute("ps", ps);
		return "admin/listProject.jsp";
	}

}
