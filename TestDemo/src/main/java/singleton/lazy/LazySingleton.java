package singleton.lazy;

/*
* 懒汉式：延迟加载
* 优点：能够编码内存浪费
* 缺点：可能创建多个实例, 加synchronized可以解决
*
* */
public class LazySingleton {

	private static LazySingleton instance;

	private LazySingleton(){}

	//同步锁
	public synchronized static LazySingleton getInstance(){
		if (instance==null){
			instance = new LazySingleton();
		}
		return instance;
	}
}
