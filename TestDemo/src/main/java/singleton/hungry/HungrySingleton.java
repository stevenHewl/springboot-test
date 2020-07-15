package singleton.hungry;


/*
饿汉式：
* 缺点：不管是否使用，都初始化了，造成内存浪费
* */
public class HungrySingleton {

	private static final  HungrySingleton instance = new HungrySingleton();

	private HungrySingleton(){}

	public static HungrySingleton getInstance(){
		return instance;
	}
}
