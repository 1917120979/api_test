package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import api.bean.Attribute;
import api.bean.Testcase;

@SuppressWarnings("serial")
public class AttributeServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		int type = Integer.parseInt(request.getParameter("type"));
		String tid = request.getParameter("tid");
		
		Attribute bean = new Attribute();
		bean.setApi(apiDAO.get(aid));
		if (tid != null && tid.length()>0) {
			Testcase testcase = tcDAO.get(Integer.parseInt(tid));
			bean.setTestcase(testcase);
		}
		bean.setName(request.getParameter("name"));
		bean.setValue(request.getParameter("value"));
		bean.setType(type);
		bean.setComments(request.getParameter("comments"));
		
		JSONObject json = new JSONObject();
		if (attrDAO.add(bean)) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", "null");
		}else {
			json.put("code", "401");
			json.put("msg", "fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();

	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		JSONObject json = new JSONObject();
		
		if (attrDAO.delete(id)) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", "null");
		}else {
			json.put("code", "401");
			json.put("msg", "fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Attribute attr = attrDAO.get(id);
		
		JSONObject json = new JSONObject();
		if (null != attr) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", attr);
		}else {
			json.put("code", "401");
			json.put("msg", "fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("attrId"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int type = Integer.parseInt(request.getParameter("type"));
		String tid = request.getParameter("tid");
		
		Attribute bean = new Attribute();
		bean.setId(id);
		bean.setApi(apiDAO.get(aid));
		if (tid != null && tid.length()>0) {
			Testcase testcase = tcDAO.get(Integer.parseInt(tid));
			bean.setTestcase(testcase);
		}
		bean.setName(request.getParameter("name"));
		bean.setValue(request.getParameter("value"));
		bean.setType(type);
		bean.setComments(request.getParameter("comments"));
		JSONObject json = new JSONObject();
		if (attrDAO.update(bean)) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", "null");
		}else {
			json.put("code", "401");
			json.put("msg", "fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
