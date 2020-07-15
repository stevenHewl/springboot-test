package singleton.lazy;

/*
内部静态类：
* 类加载顺序，先静态后动态
* 只要在访问之前就把对象创建好，就不会有线程安全问题
优点：避免了锁，提供了性能，保证了线程安全
* */
public class LazyInnerClassSingleton {

	// 构造方法，要进行判断，避免非法创建
	private LazyInnerClassSingleton(){
		if (LazyHolder.inner!=null){
			throw new RuntimeException("不允许非法创建对象");
		}
	}

	public static final LazyInnerClassSingleton getInstance(){
		return LazyHolder.inner;
	}

	private static class LazyHolder{
		private static final LazyInnerClassSingleton inner = new LazyInnerClassSingleton();
	}
}
