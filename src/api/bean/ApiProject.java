package api.bean;

public class ApiProject {
	private int id;
	private String proName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[project id:" + id + ", name:" + proName + "]";
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
}
