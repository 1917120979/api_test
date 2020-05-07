package api.bean;

public class Api {
	private int id;
	private Project project;	
	private String name;
	private int protocol;
	private String url;
	private String method;
	private int dataType;
	private int filesUpload;
	private String comments;
	
	@Override
	public String toString() {
		return String.format("project[id:%d, name:%s, method:%s, dataType:%d]",id,name,method,dataType);
	}
	public int getId() {
		return id;
	}

	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public int getFilesUpload() {
		return filesUpload;
	}
	public void setFilesUpload(int filesUpload) {
		this.filesUpload = filesUpload;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
