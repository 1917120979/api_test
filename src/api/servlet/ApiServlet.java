package api.servlet;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Api;
import api.bean.Group;
import api.bean.Project;
import api.bean.Variable;

@SuppressWarnings("serial")
public class ApiServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {		
		int pid = Integer.parseInt(request.getParameter("pid"));
		int gid = Integer.parseInt(request.getParameter("gid"));
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		Project project = pDAO.get(pid);
		Group group = gDAO.get(gid);
						
		Api bean = new Api();		
		bean.setProject(project);
		bean.setGroup(group);
		bean.setName(request.getParameter("name"));
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
			List<Variable> hvs = vDAO.list(pid, 2);
			List<Variable> pvs = vDAO.list(pid, 3);
			if (pvs != null && pvs.size() > 0) {
				for (Variable pv : pvs) {
					attrDAO.addPublicVar(pv, bean.getId());
				}			
			}
			if (hvs != null && hvs.size() > 0) {
				for (Variable hv : hvs) {
					attrDAO.addPublicVar(hv, bean.getId());
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
	public String delete(HttpServletRequest request, HttpServletResponse response) {
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
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Api bean = apiDAO.get(id);

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
	public String update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		//int gid = Integer.parseInt(request.getParameter("gid"));
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		//Group group = gDAO.get(gid);
				
		Api bean = new Api();
		bean.setId(id);
		//bean.setGroup(group);
		bean.setName(request.getParameter("name"));
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
	public String list(HttpServletRequest request, HttpServletResponse response) {		
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<Group> gs = gDAO.list(pid);	
		Project p = pDAO.get(pid);
			
		request.setAttribute("p", p);
		request.setAttribute("gs", gs);

		return "pages/listApi.jsp";
	}

}
