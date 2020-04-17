package api.bean;

public class ApiInfo {
	private int id;
	private Project project;
	private Group group;
	private String apiName;
	private String url;
	private String method;
	private int dataType;
	private int hasExtractor;
	private int hasAssert;
	
	@Override
	public String toString() {
		return String.format("project[id:%d, name:%s, method:%s, dataType:%d, hasExtractor:%d, hasAssert:%d]",id,apiName,method,dataType,hasExtractor,hasAssert);
	}
	public int getId() {
		return id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getHasExtractor() {
		return hasExtractor;
	}

	public void setHasExtractor(int hasExtractor) {
		this.hasExtractor = hasExtractor;
	}

	public int getHasAssert() {
		return hasAssert;
	}

	public void setHasAssert(int hasAssert) {
		this.hasAssert = hasAssert;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
