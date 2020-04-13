package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.Extractor;
import api.util.Page;

@SuppressWarnings("serial")
public class ExtractorServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("extr_aid"));
		Extractor bean = new Extractor();
		bean.setApiInfo(apiDAO.get(aid));
		bean.setName(request.getParameter("variableName"));
		bean.setExpression(request.getParameter("expression"));
		
		JSONObject json = new JSONObject();
		if (eDAO.add(bean)) {
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
		if (eDAO.delete(id)) {
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
		Extractor bean = eDAO.get(id);
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
		int id = Integer.parseInt(request.getParameter("extrId"));
		int aid = Integer.parseInt(request.getParameter("extr_aid"));
		Extractor bean = new Extractor();
		bean.setId(id);
		bean.setApiInfo(apiDAO.get(aid));
		bean.setName(request.getParameter("variableName"));
		bean.setExpression(request.getParameter("expression"));
		
		JSONObject json = new JSONObject();
		if (eDAO.update(bean)) {
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
