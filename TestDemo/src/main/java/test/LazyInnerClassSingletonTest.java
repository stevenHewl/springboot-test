package test;

import singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

public class LazyInnerClassSingletonTest {
	public static void main(String[] args) {
		Object obj1 = LazyInnerClassSingleton.getInstance();


		//非法创建 , 每次创建的实例都不同
		Class<?> clazz = LazyInnerClassSingleton.class;
		try {
			Constructor c = clazz.getDeclaredConstructor(null);
			c.setAccessible(true);

			Object obj2 = c.newInstance();
			Object obj3 = c.newInstance();
			Object obj4 = c.newInstance();

			System.out.println(obj2);
			System.out.println(obj3);
			System.out.println(obj4);


		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
