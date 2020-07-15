package singleton.hungry;

/*
饿汉式：
* 缺点：不管是否使用，都初始化了，造成内存浪费
* */
public class HungryStaticSingleton {
	private static final HungryStaticSingleton instance;

	//静态块
	static {
		instance = new HungryStaticSingleton();
	}

	public HungryStaticSingleton(){}

	public static HungryStaticSingleton getInstance(){
		return instance;
	}
}
