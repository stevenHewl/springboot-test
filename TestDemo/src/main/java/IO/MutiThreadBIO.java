package IO;

import IO.ThreadHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutiThreadBIO {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		try {
			ServerSocket serverSocket = new ServerSocket(888);
			ThreadHandler threadHandler;
			while (true){  //等待客户端连接
				Socket socket = serverSocket.accept(); //阻塞等待连接
				System.out.println("已连接：" + socket.getPort());
				threadHandler = new ThreadHandler(socket);
				executorService.submit(threadHandler);
			}
		}catch (IOException es){
			es.printStackTrace();
		}
	}
}
