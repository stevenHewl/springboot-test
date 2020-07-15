package springbootTest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true) // 反序列化不能识别的属性
// @JsonIgnoreProperties({ "internalId", "secretKey" }) //反序列化时，忽略具体的字段
public class User implements Serializable {
	public User() {}
	
	public User(String userName, String email, String nickName, String passWord, String regTime)
	{
		this.username = userName;
		this.email = email;
		this.pass_word = passWord;
		this.nick_name = nickName;
		this.reg_time = regTime;
		this.salt = "";
		this.state = 1;
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	public long id;
	
	@Column(nullable = false, unique = true)
	public String username;
	
	@Column(nullable = false)
	public String pass_word;
	
	@Column(nullable = false, unique = true)
	public String email;
	
	@Column(nullable = true, unique = true)
	public String nick_name;
	
	@Column(nullable = false)
	public String reg_time;
	
	private String salt;// 加密密码的盐
	private int state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
	@JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<SysRole> roleList;// 一个用户具有多个角色

	public long getid() {
		return id;		 
	}	 	 
	public void setid(long id) {
		this.id = id;	  
	}
	
	public String getuserName() {
		return username;
	}	 	 

	public void setuserName(String userName) {
		this.username = userName;
	}

	public String getpassWord() {
		return pass_word;
	}	 	 

	public void setpassWord(String passWord) {
		this.pass_word = passWord;
	}
	
	public String getemail() {
		return email;		 
	}		

	public void setemail(String email) {
		this.email = email;	  
	}
	
	public String getnickName() {
		return nick_name;
	}	 	 

	public void setnickName(String nickName) {
		this.nick_name = nickName;
	}
	
	public String getregTime() {
		return reg_time;
	}	 	 

	public void setregTime(String regTime) {
		this.reg_time = regTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 密码盐.
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.username; // + this.salt;
	}
	// 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}