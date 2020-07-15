package rpcServer;

import rpc.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandler implements Runnable {
	Socket socket;
	Object service;

	public ProcessHandler(Socket socket, Object service){
		this.socket = socket;
		this.service = service;
	}

	@Override
	public void run(){
		try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())){
			// 获得请求参数
			//对象输入流，java中序列化的实现
			RpcRequest request = (RpcRequest)inputStream.readObject();
			Object result = invoke(request);
			// 把结果写入到输出流
			outputStream.writeObject(result);
			outputStream.flush();
		}catch (Exception es){
		}
	}

	//进行反射调用
	private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		System.out.println("服务端开始反射调用方法："+ request);
		Class clazz = Class.forName(request.getClassName());
		Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
		Object result = method.invoke(service, request.getParameters());
		return result;
	}
}
