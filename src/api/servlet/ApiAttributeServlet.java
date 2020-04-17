package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiAttribute;
import api.bean.ApiInfo;
import api.bean.Assert;
import api.bean.DebugResult;
import api.bean.RegularExtractor;
import api.util.Page;

@SuppressWarnings("serial")
public class ApiAttributeServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		ApiAttribute bean = new ApiAttribute();
		bean.setApiInfo(apiDAO.get(aid));
		bean.setAttributeName(request.getParameter("attributeName"));
		bean.setAttributeValue(request.getParameter("attributeValue"));
		bean.setType(type);
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
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
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
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApiAttribute attr = attrDAO.get(id);
		
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
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("attrId"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		ApiAttribute bean = new ApiAttribute();
		
		bean.setId(id);
		bean.setAttributeName(request.getParameter("attributeName"));
		bean.setAttributeValue(request.getParameter("attributeValue"));
		bean.setType(type);
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
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		String type = request.getParameter("type");
		int aid = Integer.parseInt(request.getParameter("aid"));
		ApiInfo api = apiDAO.get(aid);
		
		List<RegularExtractor> extrs = eDAO.list(aid);
		List<Assert> asserts = assertDAO.list(aid);
		List<DebugResult> drs = drDAO.list(aid);
		List<ApiAttribute> attrs = null;
		if (null == type) {
			type = "-1";
			attrs = attrDAO.list(aid);
		}else {
			if (type.equals("0")) {
				attrs = attrDAO.list(aid, 0);
			}
			if (type.equals("1")) {
				attrs = attrDAO.list(aid, 1);
			}	
		}
		request.setAttribute("api", api);
		request.setAttribute("attrs", attrs);
		request.setAttribute("type", type);
		request.setAttribute("extrs", extrs);
		request.setAttribute("asserts", asserts);
		request.setAttribute("drs", drs);
		return "admin/listApiAttribute.jsp";
	}

}
