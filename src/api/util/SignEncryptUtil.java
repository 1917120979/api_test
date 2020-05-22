package api.util;

import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.dao.VariableDAO;
import apitest.utils.encrypt.Aes;
import apitest.utils.encrypt.Md5;
import apitest.utils.encrypt.SignCore;

public class SignEncryptUtil {
	private static final Logger logger = LoggerFactory.getLogger(SignEncryptUtil.class);
	private static String appsecret;
	static {
		VariableDAO varDAO = new VariableDAO();
		appsecret = varDAO.getValue("appsecret");
		logger.debug("appsecret:"+appsecret);
	}
	
	public static Map<String, String> getSignedMap(Map<String, String> header, String data) {
		Map<String, String> signMap = header;
		String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		signMap.put("data", data);
		signMap.put("timestamp", timestamp);
		
		signMap.remove("Content-Type");
		String sign = Md5.sign( SignCore.createLinkString(signMap), appsecret, "");
		header.put("timestamp", timestamp);
		header.put("sign", sign);
		return header;		
	}
	
	public static String getEncryptData(String data) {
		return Aes.encryptAes(data,appsecret);
	}
		
	public static JSONObject replaceData(String result,String appsecret) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		try {

			Object data = jsonObject.get("data");
			if (data != null && !data.equals("null")) {			
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
