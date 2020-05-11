package api.bean;

/**
 * 
 * @ClassName:  RegularExtractor   
 * @Description:封装正则提取器类，对外提供获取/设置属性的方法  
 * @author: liuyang
 * @date:   2020年4月16日 下午9:18:52      
 * @Copyright:
 */
public class Extractor {
	private int id;
	private Api api;
	private Testcase testcase;
	private String extractorName;
	private String variableName;
	private String regularExpression;
	private String comments;
	public Testcase getTestcase() {
		return testcase;
	}
	public void setTestcase(Testcase testcase) {
		this.testcase = testcase;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Api getApi() {
		return api;
	}
	public void setApi(Api api) {
		this.api = api;
	}
}
