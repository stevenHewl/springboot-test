package IOCTest;

public class HelloWorld {

    public HelloWorld(){}

    private String userName;
    public HelloWorld(String text){
        this.userName = text;
        System.out.println(text);
    }

    public String getUserName(){
        System.out.println(this.userName);
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}
