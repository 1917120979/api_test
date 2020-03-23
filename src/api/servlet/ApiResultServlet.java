package api.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.bean.ApiInfo;
import api.bean.ApiResult;
import api.util.HttpClientUtil;
import api.util.Page;

public class ApiResultServlet extends BaseBackServlet {
	private static final Logger logger = LoggerFactory.getLogger(ApiResultServlet.class);

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int apiId = Integer.parseInt(request.getParameter("apiId"));
		ApiInfo api = aiDAO.get(apiId);
		String requestJson = "";

		Map<String, String> headerMap = aattDAO.getHeaderMap(apiId);

		Map<String, String> requestMap = null;
		ApiResult bean = new ApiResult();

		String result = null;
		String url = "";
		if (api.getPortNum() != null && !api.getPortNum().equals("")) {
			url = api.getProtocol() + "://" + api.getServerName() + ":" + api.getPortNum() + api.getPath();
		} else {
			url = api.getProtocol() + "://" + api.getServerName() + api.getPath();
		}
		logger.info("请求url-----"+url);
		try {
			String dataType = api.getDataType();
			logger.info("dataType-----"+dataType);
			if (dataType !=null && dataType.length() > 0) {
				if (dataType.toLowerCase().contains("json")) {
					requestJson = aattDAO.getRequestJson(apiId);
					bean.setRequest(requestJson);
					logger.info("requestJson----"+requestJson);
					result = HttpClientUtil.doPostJson(url, requestJson, headerMap);

				} 
			}else {
				requestMap = aattDAO.getRequestMap(apiId);
				
				bean.setRequest(requestMap.toString());
				logger.info("requestMap----"+requestMap);
				if (api.getMethod().equals("post")) {
					result = HttpClientUtil.doPost(url, requestMap, headerMap);
				}
				if (api.getMethod().equals("get")) {
					result = HttpClientUtil.doGet(url, requestMap);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}

		/*
		 * String token = rDAO.get("token").getRelationValue(); String reslut =
		 * CommonUtil.getResponse(requestJson,headerMap,token);
		 * 
		 * ApiResult bean = new ApiResult(); bean.setApiId(apiId);
		 * bean.setRequest(requestJson); bean.setResponse(reslut);
		 */
	
		
		bean.setResponse(result);
		bean.setApiId(apiId);
		arDAO.add(bean);
		return "@admin_apiAttribute_list?apiId=" + apiId;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {		
		int apiId = 0;
		String key = request.getQueryString();
		if (key.contains("apiId")) {
			apiId = Integer.parseInt(request.getParameter("apiId"));
			arDAO.deleteAll(apiId);
		}else {		
			int id = Integer.parseInt(request.getParameter("id"));
			apiId = arDAO.get(id).getApiId();
			arDAO.delete(id);
		}		
		return "@admin_apiAttribute_list?apiId=" + apiId;
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
