package api.bean;

public class ApiGroup {
	private int id;
	private Project apiProject;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getApiProject() {
		return apiProject;
	}

	public void setApiProject(Project apiProject) {
		this.apiProject = apiProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
