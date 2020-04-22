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
	private int sign;
	private int encrypt;
	private User user;
	private String createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public int getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(int encrypt) {
		this.encrypt = encrypt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return String.format("project[id:%d, name:%s, sign:%d, encrypt:%d, createDate:%s]", id, name, sign, encrypt,createDate);
	}

}
