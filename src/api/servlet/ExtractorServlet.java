package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.Api;
import api.bean.Extractor;
import api.util.Page;

/**
 * 
 * @ClassName:  RegularExtractorServlet   
 * @Description:继承抽象类，实现抽象方法，进行前端和后端的交互  
 * @author: liuyang
 * @date:   2020年4月16日 下午10:33:07      
 * @Copyright:
 */
@SuppressWarnings("serial")
public class ExtractorServlet extends BaseBackServlet{
	private static final Logger logger = LoggerFactory.getLogger(ExtractorServlet.class);
	
	/**
	 * 
	 * <p>Title: add</p>   
	 * <p>Description: 新增成功后，将apiInfo中的标志位hasExtractor置为1</p>   
	 * @param request
	 * @param response
	 * @param page
	 * @return   
	 * @see api.servlet.BaseBackServlet#add(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, api.util.Page)
	 */
	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		Api apiInfo = apiDAO.get(aid);
		
		Extractor bean = new Extractor();		
		bean.setApi(apiInfo);
		bean.setVariableName(request.getParameter("variableName"));
		bean.setExtractorName(request.getParameter("extractorName"));
		bean.setRegularExpression(request.getParameter("regularExpression"));
		bean.setComments(request.getParameter("comments"));
		JSONObject json = new JSONObject();	
		if (reDAO.add(bean)) {
			json.put("code", "0");
			json.put("msg", "add success");
			json.put("data", bean);
		}else {
			json.put("code", "401");
			json.put("msg", "add fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}
	
	/**
	 * 
	 * <p>Title: delete</p>   
	 * <p>Description: 删除提取器后,如果接口没有提取器了，将apiInfo中的标志位hasExtractor置为0</p>   
	 * @param request
	 * @param response
	 * @param page
	 * @return   
	 * @see api.servlet.BaseBackServlet#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, api.util.Page)
	 */
	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));		
		JSONObject json = new JSONObject();
		if (reDAO.delete(id)) {
			json.put("code", "0");
			json.put("msg", "delete success");
			json.put("data", "null");		
		}else {
			json.put("code", "401");
			json.put("msg", "delete fail");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Extractor bean = reDAO.get(id);
		
		JSONObject json = new JSONObject();
		if (bean != null) {
			json.put("code", "0");
			json.put("msg", "get success");
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
	public String update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		Extractor bean = new Extractor();
		bean.setId(id);
		bean.setApi(apiDAO.get(aid));
		bean.setExtractorName(request.getParameter("extractorName"));
		bean.setRegularExpression(request.getParameter("regularExpression"));
		
		JSONObject json = new JSONObject();
		if (reDAO.update(bean)) {
			json.put("code", "0");
			json.put("msg", "update success");
			json.put("data", bean);
		}else {
			json.put("code", "401");
			json.put("msg", "update fail");
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
