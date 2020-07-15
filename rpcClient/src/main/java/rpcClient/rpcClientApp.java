package rpcClient;

import rpc.IUserService;

import java.lang.reflect.Proxy;

public class rpcClientApp {
	public static void main(String[] args) throws Throwable {
		// 调用接口方法时，会通过InvocationHandler的invoke方法来动态调用远程方法
		RemoteInvocationHandler remoteInvocationHandler = new RemoteInvocationHandler("localhost", 8080);
		RpcProxyClient rpcProxyClient = new RpcProxyClient();

		// 初始化一个代理对象
		IUserService userService = rpcProxyClient.initClientProxy(IUserService.class, remoteInvocationHandler);

		// 实际会调用代理方法invoke
		//Object result = userService.doSave("steven");
		Object result = remoteInvocationHandler.invoke(userService,
				IUserService.class.getMethod("doSave",new Class[]{String.class}),new Object[]{"steven"});

		System.out.println(result);
	}
}
