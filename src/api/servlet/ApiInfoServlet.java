package api.servlet;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.Group;
import api.bean.Project;
import api.bean.Variable;
import api.util.Page;

@SuppressWarnings("serial")
public class ApiInfoServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {		
		int pid = Integer.parseInt(request.getParameter("pid"));
		int gid = Integer.parseInt(request.getParameter("gid"));
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		Project project = pDAO.get(pid);
		Group group = gDAO.get(gid);
		
		
		
		ApiInfo bean = new ApiInfo();
		
		bean.setProject(project);
		bean.setGroup(group);
		bean.setApiName(request.getParameter("apiName"));
		bean.setUrl(request.getParameter("url"));			
		bean.setMethod(request.getParameter("method"));
		bean.setDataType(dataType);
		bean.setHasExtractor(0);
		bean.setHasAssert(0);

		JSONObject json = new JSONObject();		
		if (apiDAO.add(bean)) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", "null");
			List<Variable> pvs = pvDAO.list(pid, 2);
			if (pvs != null && pvs.size() > 0) {
				for (Variable pv : pvs) {
					attrDAO.add(pv, bean.getId());
				}			
			}
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
		if (apiDAO.delete(id)) {
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
		ApiInfo bean = apiDAO.get(id);

		JSONObject json = new JSONObject();		
		if (bean != null) {
			json.put("msg", "sucess");
			json.put("code", 0);
			json.put("data", bean);
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
		int id = Integer.parseInt(request.getParameter("aid"));
		//int pid = Integer.parseInt(request.getParameter("pid"));
		//int gid = Integer.parseInt(request.getParameter("gid"));
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		//Project project = pDAO.get(pid);
		//Group group = gDAO.get(gid);
		
		
		ApiInfo bean = new ApiInfo();
		bean.setId(id);
		//bean.setProject(project);
		//bean.setGroup(group);
		bean.setApiName(request.getParameter("apiName"));
		bean.setUrl(request.getParameter("url"));			
		bean.setMethod(request.getParameter("method"));
		bean.setDataType(dataType);

		JSONObject json = new JSONObject();		
		if (apiDAO.update(bean)) {
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
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<Group> gs = gDAO.list(pid);
		
		Project p = pDAO.get(pid);
			
		request.setAttribute("p", p);
		request.setAttribute("gs", gs);

		return "admin/listApiInfo.jsp";
	}

}
