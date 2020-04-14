package api.servlet;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.DebugResult;
import api.bean.Project;
import api.util.HttpClientUtil;
import api.util.Page;

@SuppressWarnings("serial")
public class DebugAPIServlet extends BaseBackServlet{

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
		
		String debugReq = "";
		String debugResp = "";
		String debugExtractor = "";
		String debugAssert = "";
		
		DebugResult bean = new DebugResult();
		
		try {
			if (isSgin == 0) {
				if (dataType == 1 && method.toUpperCase().equals("POST")) {
					String requestJson = attrDAO.getRequestJson(aid);
					debugResp = HttpClientUtil.doPostJson(url, requestJson, headerMap);
					debugReq = url+"\r\n"+requestJson+"\r\n"+headerMap.toString();
					
				}else {
					Map<String, String> requestMap = attrDAO.getRequestMap(aid);
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

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
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
