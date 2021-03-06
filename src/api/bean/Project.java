package api.bean;

/**
 * 
 * @ClassName: Project
 * @Description:封装Project并对外提供属性获取的方法
 * @author: Durant2035
 * @date: 2020年4月15日 下午8:32:43
 * @Copyright:
 */
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

	@Override
	public String toString() {
		return String.format("project[id:%d, name:%s, isSign:%d, isEncript:%d]", id, name, isSign, isEncript);
	}

}
