package api.bean;

/**
 * 
 * @ClassName:  Variable   
 * @Comments:TODO(描述这个类的作用)   
 * @author: liuyang
 * @date:   2020年4月23日 下午10:26:32      
 * @Copyright:
 * type:1 用户自定义变量；2 接口关联 ；3 公共-header ；4 公共-参数
 */
public class Variable {
	private int id;
	private Project project;
	private String name;
	private String value;
	private int type;
	private String comments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}


}
