package ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

	static userService userservice = new userServiceIml();

	public static void main(String[] args) {
		userService proxy = (userService) Proxy.newProxyInstance(
				userServiceIml.class.getClassLoader(), userServiceIml.class.getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("before run target method:" + method.getName());
						Object obj = method.invoke(userservice, args);
						System.out.println("after run target method：" + method.getName());
						return obj;
					}
				}
		);
		proxy.getUser();
		proxy.setUser("steven");
	}
}
