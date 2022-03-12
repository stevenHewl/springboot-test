package singleton.lazy;


/*
* 由于懒汉模式加了synchronized锁定了方法，于是出现了双重检查模式 DCL
* 避免阻塞方法，但第一次创建还是会阻塞
* */
public class LazyDoubleCheckSingleton {
	// volatile 关键字避免
	private volatile static LazyDoubleCheckSingleton instance;

	private LazyDoubleCheckSingleton(){}

	public static LazyDoubleCheckSingleton getInstance(){
		// 只有创建对象时才加锁,减少阻塞
		if (instance == null) {
			synchronized (LazyDoubleCheckSingleton.class) {
				if (instance == null) {
					instance = new LazyDoubleCheckSingleton();
				}
			}
		}
		return instance;
	}
}
