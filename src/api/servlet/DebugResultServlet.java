package api.servlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.ApiInfo;
import api.bean.Assert;
import api.bean.DebugResult;
import api.bean.Project;
import api.bean.RegularExtractor;
import api.util.HttpClientUtil;
import api.util.Page;
import api.util.SignAndEncriptUtil;

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
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		ApiInfo apiInfo = apiDAO.get(aid);
		
		logger.debug("调试接口是>>>"+apiInfo.toString());
		
		Project project = apiInfo.getProject();
		int isSgin = project.getIsSign();
		int isEncrypt = project.getIsEncrypt();
		int dataType = apiInfo.getDataType();
		String url = apiInfo.getUrl();
		String method = apiInfo.getMethod();
		
		Map<String, String> headerMap = attrDAO.getHeaderMap(aid);
		Map<String, String> requestMap = attrDAO.getRequestMap(aid);
		String requestJson = attrDAO.getRequestJson(aid);
		
		String debugRequest = "";
		String debugResponse = "";
		String debugExtractor = "";
		String debugAssert = "";
		
		DebugResult bean = new DebugResult();
		
		try {	
			
			if (isSgin == 0) {				
				if (dataType == 1 && method.toUpperCase().equals("POST")) {
					debugResponse = HttpClientUtil.doPostJson(url, requestJson, headerMap);					
				}else {
					if (method.toUpperCase().equals("POST")) {
						debugResponse = HttpClientUtil.doPost(url, headerMap, requestMap);					
					}else if (method.toUpperCase().equals("GET")) {
						debugResponse = HttpClientUtil.doGet(url, headerMap, requestMap);
					}						
				}
			}
			if (isSgin == 1) {
				if (isEncrypt == 1) {
					String appsecret = pvDAO.getString("appsecret");
					if (appsecret.length() > 0) {
						if (dataType == 1) {
							if (method.toUpperCase().equals("GET")) {
								debugResponse = SignAndEncriptUtil.getGetGatewayResponse(url, headerMap, appsecret);
							}else if (method.toUpperCase().equals("POST")) {
								debugResponse = SignAndEncriptUtil.getPostGatewayResponse(url, headerMap, requestJson, appsecret);
							} else {
								debugResponse = "method方法不支持";
							}
						}else {
							debugResponse = "数据类型必须是json";
						}
					}else {
						debugResponse = "未配置appsecret";
					}
				}else {
					debugResponse = "需要选择加密类型";
				}		
			}
			if (isSgin == 2) {
				debugResponse = "目前不支持";
			}
			if (isSgin == 3) {
				debugResponse = "目前不支持";
			}
			
			debugRequest = String.format("请求地址：%s，请求头是：%s，请求数据是：%s", url, headerMap.toString(), requestMap.toString());
			if (apiInfo.getHasExtractor() == 1) {
				List<RegularExtractor> extractors = reDAO.list(aid);
				for (int i = 0; i < extractors.size(); i++) {
					String regex = extractors.get(i).getRegularExpression();
					debugExtractor += super.getExtractorValue(debugResponse, regex)+",";
				}
			}
			if (apiInfo.getHasAssert() == 1) {
				int flag = 1;
				List<Assert> asserts = assertDAO.list(aid);
				for (int i = 0; i < asserts.size(); i++) {
					String regex = asserts.get(i).getAssertExpress();
					String actual = super.getExtractorValue(debugResponse, regex);
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
		logger.debug("调试请求是>>>"+debugRequest);
		logger.debug("调试响应是>>>"+debugResponse);
		logger.debug("调试提取器是>>>"+debugExtractor);
		logger.debug("调试断言是>>>"+debugAssert);

		bean.setApiInfo(apiInfo);
		bean.setDebugReq(debugRequest);
		bean.setDebugResp(debugResponse);
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
