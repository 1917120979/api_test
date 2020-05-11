package api.bean;

public class DebugResult {
	private int id;
	private Api api;
	private String debugTime;
	private String debugRequest;
	private String debugRespose;
	private String debugPost;

	public Api getApi() {
		return api;
	}
	public void setApi(Api api) {
		this.api = api;
	}
	public String getDebugTime() {
		return debugTime;
	}
	public void setDebugTime(String debugTime) {
		this.debugTime = debugTime;
	}
	public String getDebugRequest() {
		return debugRequest;
	}
	public void setDebugRequest(String debugRequest) {
		this.debugRequest = debugRequest;
	}
	public String getDebugRespose() {
		return debugRespose;
	}
	public void setDebugRespose(String debugRespose) {
		this.debugRespose = debugRespose;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDebugPost() {
		return debugPost;
	}
	public void setDebugPost(String debugPost) {
		this.debugPost = debugPost;
	}
	

}
