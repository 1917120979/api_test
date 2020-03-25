package api.bean;

public class ApiInfo {
	private int id;
	private int pid;
	private int gid;
	private String apiName;
	private String protocol;
	private String serverName;
	private String portNum;
	private String method;
	private String path;
	private String dataType;
	private int isRelation;

	public int getId() {
		return id;
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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPortNum() {
		return portNum;
	}

	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(int isRelation) {
		this.isRelation = isRelation;
	}

}
