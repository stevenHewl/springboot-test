package ProxyTest;

public class userServiceIml implements userService {

	String userName = "default name";

	public void getUser(){
		System.out.println(userName);
	}

	public void setUser(String userName){
		this.userName = userName;
		System.out.println(userName);
	}
}
