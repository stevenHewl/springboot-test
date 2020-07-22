package IO;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadHandler implements Runnable{

	Socket socket;
	public ThreadHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			Scanner input = new Scanner(this.socket.getInputStream());
			while (true) { //不断读写客户端的请求
				String request = input.nextLine(); // 等待客户端数据，占用线程，不能做别的操作
				if ("quit".equals(request)) {
					break;
				}
				System.out.println(String.format("From %s : %s", socket.getRemoteSocketAddress(), request));
				String response = String.format("From %s : %s", socket.getLocalSocketAddress(), request);
				socket.getOutputStream().write(response.getBytes());
			}
		}catch (IOException es){
			es.printStackTrace();
		}
	}
}
