package api.bean;

public class Relation {
	private int id;
	private int apiId;
	private String relationName;
	private String relationValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getRelationValue() {
		return relationValue;
	}
	public void setRelationValue(String relationValue) {
		this.relationValue = relationValue;
	}
	public int getApiId() {
		return apiId;
	}
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	
	

}
