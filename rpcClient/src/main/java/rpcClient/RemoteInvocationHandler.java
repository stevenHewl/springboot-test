package rpcClient;

import rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//动态代理要执行的方法实现
public class RemoteInvocationHandler implements InvocationHandler {
	private String host;
	private Integer port;

	public RemoteInvocationHandler(String host, Integer port){
		this.host = host;
		this.port = port;
	}

	// proxy the proxy instance that the method was invoked on
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		// 当调用指定接口的方法时，会被代理执行这个invoke方法
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setTypes(method.getParameterTypes());
		rpcRequest.setParameters(args);

		// 通过网络IO请求调用远程实现代码
		RpcNetTransport rpcNetTransport = new RpcNetTransport(this.host, this.port);
		return rpcNetTransport.send(rpcRequest);
	}
}
