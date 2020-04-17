package api.bean;

import java.util.List;

public class Group {
	private int id;
	private Project project;
	private String name;
	private List<ApiInfo> apiByGroup;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ApiInfo> getApiByGroup() {
		return apiByGroup;
	}

	public void setApiByGroup(List<ApiInfo> apiByGroup) {
		this.apiByGroup = apiByGroup;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
