package model.user;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:15
 */
public class User {
	public enum eSex{
		eMale("남자"),
		eFemale("여자")
		;
		private String title;
		private eSex(String title) {
			this.title = title;
		}
		public String getTitle() {
			return this.title;
		}
	}

	private String id;
	private String password;
	private Authority authority;
	private int age;
	private eSex sex;
	
	public int getAge() {
		return age;
	}

	public User(){

	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public eSex getSex() {
		return sex;
	}

	public void setSex(eSex sex) {
		this.sex = sex;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Authority getAuthority() {
		return this.authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	public boolean equals(String id) {
		return this.id.equals(id);
	}

	public void finalize() throws Throwable {

	}

}