package api.bean;

public class DebugResult {
	private int id;
	private Api apiInfo;
	private String date;
	private String debugReq;
	private String debugResp;
	private String debugExtractor;
	private String debugAssert;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Api getApiInfo() {
		return apiInfo;
	}
	public void setApiInfo(Api apiInfo) {
		this.apiInfo = apiInfo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDebugReq() {
		return debugReq;
	}
	public void setDebugReq(String debugReq) {
		this.debugReq = debugReq;
	}
	public String getDebugResp() {
		return debugResp;
	}
	public void setDebugResp(String debugResp) {
		this.debugResp = debugResp;
	}
	public String getDebugExtractor() {
		return debugExtractor;
	}
	public void setDebugExtractor(String debugExtractor) {
		this.debugExtractor = debugExtractor;
	}
	public String getDebugAssert() {
		return debugAssert;
	}
	public void setDebugAssert(String debugAssert) {
		this.debugAssert = debugAssert;
	}

}
