package rpcServer;

import sun.java2d.loops.ProcessPath;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {

	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	//发布服务, 把服务发布到网络接口，网络接口接收到相应的调用请求后，可以调用
	public void publish(Object service, int port){
		ServerSocket serverSocket = null; // 阻塞IO
		try {
			serverSocket = new ServerSocket(port);  // 监听一个tcp协议的端口号
			while (true){
				Socket socket = serverSocket.accept(); //获得一个客户端连接（阻塞直到客户端连接过来）
				System.out.println("收到客户端连接：" + socket.getPort());
				// io 阻塞可以通过多线程 完成，避免一直阻塞

				//线程池异步处理IO
				executorService.submit(new ProcessHandler(socket, service));
			}
		} catch (Exception es){
			es.printStackTrace();
		}
	}
}
