package api.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.Api;
import api.bean.Assert;
import api.bean.DebugResult;
import api.bean.Project;
import api.bean.Extractor;
import api.util.HttpClientUtil;
import api.util.SignEncryptUtil;

@SuppressWarnings("serial")
public class DebugResultServlet extends BaseBackServlet{
	private static final Logger logger = LoggerFactory.getLogger(DebugResultServlet.class);
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
	public String add(HttpServletRequest request, HttpServletResponse response) {
		String debugRequest = "";
		String debugResponse = "";
		String debugExtractor = "";
		String debugAssert = "";
		int aid = Integer.parseInt(request.getParameter("aid"));
		Map<String, String> headerMap = attrDAO.getMap(aid, 2);
		Map<String, String> requestMap = attrDAO.getMap(aid, 3);
		String data = attrDAO.getJson(aid, 3);
		
		Api api = apiDAO.get(aid);
		int dataType = api.getDataType();
		String url = api.getUrl();
		String method = api.getMethod();
		Project project = api.getProject();
		int isSgin = project.getSign();
		
		if (isSgin == 1 && null != headerMap) {
			SignEncryptUtil.getSignedMap(headerMap, data);
		}
		if (project.getEncrypt() == 1 && data != null) {
			SignEncryptUtil.getEncryptData(data);
		}
			
		switch (method) {
		case "GET":
			debugRequest += headerMap.toString() +";"+  requestMap.toString();
			debugResponse = HttpClientUtil.doGet(url, headerMap, requestMap);
			break;
		case "POST":
			if (dataType == 1) {
				debugRequest += headerMap.toString() +";"+  data;
				debugResponse = HttpClientUtil.doPostJson(url, headerMap, data);				
			}else {
				debugRequest += headerMap.toString() +";"+  requestMap.toString();
				debugResponse = HttpClientUtil.doPost(url, headerMap, requestMap);	
			}
			break;
		default:
			debugResponse = "method方法不支持";
			break;
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		List<Extractor> extractors = reDAO.list(aid, 0);
		if (extractors.size() > 0 && null != extractors) {
			for (int i = 0; i < extractors.size(); i++) {
				Extractor bean = extractors.get(i);
				String regex = bean.getRegularExpression();
				String key = bean.getVariableName();
				String value = super.getExtractorValue(debugResponse, regex);
				resultMap.put(key, value);
			}
		}
		
		List<Assert> asserts = assertDAO.list(aid, 0);
		if (asserts.size() > 0 && null != asserts) {
			for (int i = 0; i < asserts.size(); i++) {
				Assert bean = asserts.get(i);
				
				String regex = bean.getAssertRegular();
				String actualValue = super.getExtractorValue(debugResponse, regex);
				String name = bean.getAssertName();
				resultMap.put(name, actualValue);
			}
		}
		DebugResult bean = new DebugResult();					
		logger.debug("调试请求是>>>"+debugRequest);
		logger.debug("调试响应是>>>"+debugResponse);
		logger.debug("调试提取器是>>>"+debugExtractor);
		logger.debug("调试断言是>>>"+debugAssert);

		bean.setApi(api);
		bean.setDebugRequest(debugRequest);
		bean.setDebugRespose(debugResponse);
		bean.setDebugPost(requestMap.toString());
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
	public String delete(HttpServletRequest request, HttpServletResponse response) {
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
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
