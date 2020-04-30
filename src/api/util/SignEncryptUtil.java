package api.util;

import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import apitest.utils.encrypt.Aes;
import apitest.utils.encrypt.Md5;
import apitest.utils.encrypt.SignCore;

public class SignEncryptUtil {
	private static final Logger logger = LoggerFactory.getLogger(SignEncryptUtil.class);
	
	public static String getPostGatewayResponse(String url, Map<String, String> header,String requestData,String appsecret) {
		Map<String,String> signMap = header;
		requestData =  Aes.encryptAes(requestData,appsecret);
		
		String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());		
		signMap.put("data", requestData);
		signMap.put("timestamp", timestamp);
		String sign = Md5.sign( SignCore.createLinkString(signMap), appsecret, "");
		
		header.put("sign", sign);
		header.put("timestamp", timestamp);			
		
		logger.info("请求头：>>>"+header.toString()+"\r\n");
		String result = HttpClientUtil.doPostJson(url, header, requestData);

		result = replaceData(result, appsecret).toJSONString();
		logger.info("解密后的返回数据：>>>"+result+"\r\n");
		return result;
	}
	
	public static String getGetGatewayResponse(String url, Map<String, String> header, String appsecret) {
		Map<String,String> signMap = header;
		
		String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		signMap.remove("Content-Type");
		signMap.put("timestamp", timestamp);
		String sign = Md5.sign( SignCore.createLinkString(signMap), appsecret, "");
		
		header.put("sign", sign);
		header.put("timestamp", timestamp);			
		
		logger.info("请求头：>>>"+header.toString()+"\r\n");
		String result = HttpClientUtil.doGet(url, header, null);

		result = replaceData(result, appsecret).toJSONString();
		logger.info("解密后的返回数据：>>>"+result+"\r\n");
		return result;
	}
	
	
	public static JSONObject replaceData(String result,String appsecret) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		try {

			Object data = jsonObject.get("data");
			if (data != null) {			
				String tempdata = Aes.decryptAes((String) data, appsecret);
				jsonObject.remove("data");
				logger.info("》》》》"+tempdata);
				if (tempdata.startsWith("{")) {
					jsonObject.put("data", JSONObject.parseObject(tempdata));
				}else {
					jsonObject.put("data", tempdata);
				}
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析错误！");
		}
		
		return jsonObject;
	}
}
