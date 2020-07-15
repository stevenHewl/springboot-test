package rpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxyClient {

	// 动态代理来调用远程接口
	public <T> T initClientProxy(final Class<T> interfaceClass, InvocationHandler invocationHandler){
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, invocationHandler);
	}
}
