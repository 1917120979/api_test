package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.Assert;
import api.util.Page;

@SuppressWarnings("serial")
public class AssertServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("ass_aid"));
		Assert bean = new Assert();
		ApiInfo apiInfo = apiDAO.get(aid);
		bean.setApiInfo(apiInfo);
		bean.setAssertExpress(request.getParameter("assertExpress"));
		bean.setAssertExpect(request.getParameter("assertExpect"));
		
		JSONObject json = new JSONObject();
		if (assertDAO.add(bean)) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", "null");
			apiInfo.setHasAssert(1);
			apiDAO.update(apiInfo);
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
		ApiInfo apiInfo = assertDAO.get(id).getApiInfo();
		JSONObject json = new JSONObject();
		if (assertDAO.delete(id)) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", "null");
			apiInfo.setHasExtractor(0);
			apiDAO.update(apiInfo);
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
		Assert bean = assertDAO.get(id);
		JSONObject json = new JSONObject();
		if (bean != null) {
			json.put("code", "0");
			json.put("msg", "success");
			json.put("data", bean);
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
		int id = Integer.parseInt(request.getParameter("assertId"));
		int aid = Integer.parseInt(request.getParameter("ass_aid"));
		Assert bean = new Assert();
		bean.setId(id);
		bean.setApiInfo(apiDAO.get(aid));
		bean.setAssertExpress(request.getParameter("assertExpress"));
		bean.setAssertExpect(request.getParameter("assertExpect"));
		
		JSONObject json = new JSONObject();
		if (assertDAO.update(bean)) {
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
		return null;
	}

}
