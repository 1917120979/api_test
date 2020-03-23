package api.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiAttribute;
import api.bean.ApiInfo;
import api.bean.ApiResult;
import api.util.Page;

public class ApiAttributeServlet extends BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int apiId = Integer.parseInt(request.getParameter("apiId"));
		int type = Integer.parseInt(request.getParameter("type"));
		ApiAttribute bean = new ApiAttribute();
		bean.setApiId(apiId);
		bean.setPropName(request.getParameter("propName"));
		bean.setPropValue(request.getParameter("propValue"));
		bean.setType(type);
		aattDAO.add(bean);
		return "@admin_apiAttribute_list?apiId="+apiId;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApiAttribute bean = aattDAO.get(id);
		aattDAO.delete(id);
		return "@admin_apiAttribute_list?apiId="+bean.getApiId();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		ApiAttribute bean = aattDAO.get(id);
		JSONObject json = new JSONObject();
		json.put("aattr", bean);
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int apiId = Integer.parseInt(request.getParameter("apiId"));
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		ApiAttribute bean = new ApiAttribute();
		bean.setId(id);
		bean.setPropName(request.getParameter("propName"));
		bean.setPropValue(request.getParameter("propValue"));
		bean.setType(type);
		aattDAO.update(bean);
		return "@admin_apiAttribute_list?apiId="+apiId;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int apiId = Integer.parseInt(request.getParameter("apiId"));
		ApiInfo api = aiDAO.get(apiId);
		List<ApiAttribute> aatts0 = aattDAO.list(apiId, 0, page.getStart(), page.getCount());
		List<ApiAttribute> aatts1 = aattDAO.list(apiId, 1, page.getStart(), page.getCount());
		
		List<ApiResult> ars = arDAO.list(apiId, page.getStart(), page.getCount());
		page.setTotal(arDAO.getTotal(apiId));
		
		request.setAttribute("api", api);
		request.setAttribute("aatts0", aatts0);
		request.setAttribute("aatts1", aatts1);
		request.setAttribute("ars", ars);
		request.setAttribute("page", page);
		
		return "admin/listAttribute.jsp";
	}

}
