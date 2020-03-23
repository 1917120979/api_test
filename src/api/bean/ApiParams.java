package api.bean;

public class ApiParams {
	private int id;
	private ApiInfo apiContent;
	private String paramName;
	private String paramValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ApiInfo getApiContent() {
		return apiContent;
	}
	public void setApiContent(ApiInfo apiContent) {
		this.apiContent = apiContent;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	@Override
	public String toString() {
		return String.format("[params id:%d, paramName:%s, paramValue:%d, apiName:%s]", id, paramName,
				paramValue, apiContent.getApiName());
	}

}
