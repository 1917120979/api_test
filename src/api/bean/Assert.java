package api.bean;

public class Assert {
	private int id;
	private ApiInfo apiInfo;
	private String assertName;
	private String assertItem;
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
	public String getAssertName() {
		return assertName;
	}
	public void setAssertName(String assertName) {
		this.assertName = assertName;
	}
	public String getAssertItem() {
		return assertItem;
	}
	public void setAssertItem(String assertItem) {
		this.assertItem = assertItem;
	}
	public String getAssertExpect() {
		return assertExpect;
	}
	public void setAssertExpect(String assertExpect) {
		this.assertExpect = assertExpect;
	}
	
}
