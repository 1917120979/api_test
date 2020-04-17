package api.bean;

/**
 * 
 * @ClassName:  RegularExtractor   
 * @Description:封装正则提取器类，对外提供获取/设置属性的方法  
 * @author: liuyang
 * @date:   2020年4月16日 下午9:18:52      
 * @Copyright:
 */
public class RegularExtractor {
	private int id;
	private ApiInfo apiInfo;
	private String extractorName;
	private String regularExpression;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ApiInfo getApiInfo() {
		return apiInfo;
	}
	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}
	public String getExtractorName() {
		return extractorName;
	}
	public void setExtractorName(String extractorName) {
		this.extractorName = extractorName;
	}
	public String getRegularExpression() {
		return regularExpression;
	}
	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}
	@Override
	public String toString() {
		return String.format("RegularExtractor[id:%d, apiInfo:%s, extractorName:%d, regularExpression:%d]", id, apiInfo.toString(), extractorName, regularExpression);
	}
}
