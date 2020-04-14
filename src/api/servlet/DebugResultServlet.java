package api.servlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.Assert;
import api.bean.DebugResult;
import api.bean.Extractor;
import api.bean.Project;
import api.util.HttpClientUtil;
import api.util.Page;

@SuppressWarnings("serial")
public class DebugResultServlet extends BaseBackServlet{
	
	/**
	 * 
	 * <p>Title: add</p>   
	 * <p>Description:执行接口，添加调试结果 </p>   
	 * @param request
	 * @param response
	 * @param page
	 * @return   
	 * @see api.servlet.BaseBackServlet#add(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, api.util.Page)
	 */
	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		ApiInfo apiInfo = apiDAO.get(aid);
		Project project = apiInfo.getProject();
		int isSgin = project.getIsSign();
		int isEncript = project.getIsEncript();
		int dataType = apiInfo.getDataType();
		String url = apiInfo.getUrl();
		String method = apiInfo.getMethod();
		
		Map<String, String> headerMap = attrDAO.getHeaderMap(aid);
		Map<String, String> requestMap = attrDAO.getRequestMap(aid);
		String requestJson = attrDAO.getRequestJson(aid);
		
		String debugReq = "";
		String debugResp = "";
		String debugExtractor = "";
		String debugAssert = "";
		
		DebugResult bean = new DebugResult();
		
		try {	
			if (isSgin == 0) {
				if (dataType == 1 && method.toUpperCase().equals("POST")) {
					debugResp = HttpClientUtil.doPostJson(url, requestJson, headerMap);
					debugReq = url+"\r\n"+requestJson+"\r\n"+headerMap.toString();
					
				}else {
					if (method.toUpperCase().equals("POST")) {
						debugResp = HttpClientUtil.doPost(url, requestMap, headerMap);
						debugReq = url+"\r\n"+requestMap.toString()+"\r\n"+headerMap.toString();
					}else if (method.toUpperCase().equals("GET")) {
						debugResp = HttpClientUtil.doGet(url, requestMap, headerMap);
						debugReq = url+"\r\n"+requestMap.toString()+"\r\n"+headerMap.toString();
					}					
				}
			}
			if (isSgin == 1) {
				
				if (isEncript == 1) {
					
				}else {
					
				}
						
			}
			if (isSgin == 2) {
				
			}
			if (isSgin == 3) {
				
			}	
			
			if (apiInfo.getHasExtractor() == 1) {
				List<Extractor> extractors = eDAO.list(aid);
				for (int i = 0; i < extractors.size(); i++) {
					String regex = extractors.get(i).getExpression();
					debugExtractor += super.getExtractorValue(debugResp, regex)+",";
				}
			}
			if (apiInfo.getHasAssert() == 1) {
				int flag = 1;
				List<Assert> asserts = assertDAO.list(aid);
				for (int i = 0; i < asserts.size(); i++) {
					String regex = asserts.get(i).getAssertExpress();
					String actual = super.getExtractorValue(debugResp, regex);
					if (actual.equals(asserts.get(i).getAssertExpect())) {
						flag *= 1;
					}else {
						flag *= 0;
					}
				}
				if (flag == 1) {
					debugAssert = "pass";
				}else {
					debugAssert = "fail";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bean.setDate(new Date());
		bean.setApiInfo(apiInfo);
		bean.setDebugReq(debugReq);
		bean.setDebugResp(debugResp);
		bean.setDebugExtractor(debugExtractor);
		bean.setDebugAssert(debugAssert);			

		JSONObject json = new JSONObject();
		if (drDAO.add(bean)) {
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

	/**
	 * 
	 * <p>Title: delete</p>   
	 * <p>Description:删除接口所有调试结果 </p>   
	 * @param request
	 * @param response
	 * @param page
	 * @return   
	 * @see api.servlet.BaseBackServlet#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, api.util.Page)
	 */
	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		JSONObject json = new JSONObject();
		if (drDAO.delete(aid)) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
