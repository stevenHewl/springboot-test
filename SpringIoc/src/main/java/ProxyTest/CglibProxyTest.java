package ProxyTest;

import org.apache.ibatis.executor.loader.WriteReplaceInterface;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyTest {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(userServiceIml.class);
		enhancer.setInterfaces(userServiceIml.class.getInterfaces());
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				System.out.println("======插入前置通知======");
				Object object = methodProxy.invokeSuper(o, objects);
				System.out.println("======插入后者通知======");
				return object;
			}
		});
		userService userService = (userService)enhancer.create();
		userService.getUser();
		userService.setUser("何文良");
	}
}
