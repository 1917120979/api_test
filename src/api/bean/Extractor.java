package api.bean;

public class Extractor {
	private int id;
	private ApiInfo apiInfo;
	private String name;
	private String expression;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
}
