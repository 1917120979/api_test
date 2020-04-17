package api.bean;

public class Assert {
	private int id;
	private ApiInfo apiInfo;
	private String assertExpress;
	private String assertExpect;
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
	
	public String getAssertExpect() {
		return assertExpect;
	}
	public void setAssertExpect(String assertExpect) {
		this.assertExpect = assertExpect;
	}
	public String getAssertExpress() {
		return assertExpress;
	}
	public void setAssertExpress(String assertExpress) {
		this.assertExpress = assertExpress;
	}

}
