package api.bean;

public class Project {
	private int id;
	private String name;
	private int isSign;
	private int isEncript;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}

	public int getIsEncript() {
		return isEncript;
	}

	public void setIsEncript(int isEncript) {
		this.isEncript = isEncript;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
