package api.bean;

public class Assert {
	private int id;
	private Api api;
	private Testcase testcase;
	private String assertName;
	private String assertRegular;
	private String expectValue;
	private String comments;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Api getApi() {
		return api;
	}
	public void setApi(Api api) {
		this.api = api;
	}
	public Testcase getTestcase() {
		return testcase;
	}
	public void setTestcase(Testcase testcase) {
		this.testcase = testcase;
	}
	public String getAssertName() {
		return assertName;
	}
	public void setAssertName(String assertName) {
		this.assertName = assertName;
	}
	public String getAssertRegular() {
		return assertRegular;
	}
	public void setAssertRegular(String assertRegular) {
		this.assertRegular = assertRegular;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getExpectValue() {
		return expectValue;
	}
	public void setExpectValue(String expectValue) {
		this.expectValue = expectValue;
	}
	
}
