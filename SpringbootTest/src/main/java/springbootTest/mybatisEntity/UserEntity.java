package springbootTest.mybatisEntity;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import springbootTest.common.UserSexEnum;

@Entity
@JsonIgnoreProperties({ "internalId", "secretKey" })
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;
	private int age;

	public UserEntity() {
		super();
	}

	public UserEntity(int id, String userName, String passWord, UserSexEnum userSex, String nickName) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
		this.nickName = nickName;
	}

	public UserEntity(int id, String userName, String passWord, UserSexEnum userSex, String nickName, int age) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
		this.nickName = nickName;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
	}

}