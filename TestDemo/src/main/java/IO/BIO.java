package com.IO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
很多初学者可能不明白为何一个socket可以accept多次。实际上socket的设计者可能特意为多客户机的情况留下了伏笔，让accept()能够返回一个新的socket。
下面是 accept 接口的原型：
     int accept(int s, struct sockaddr *addr, socklen_t *addrlen);
输入参数s是从socket()，bind()和listen()中沿用下来的socket句柄值。执行完bind()和listen()后，操作系统已经开始在指定的端口处监听所有的连接请求，
如果有请求，则将该连接请求加入请求队列。调用accept()接口正是从 socket s 的请求队列抽取第一个连接信息，创建一个与s同类的新的socket返回句柄。
新的socket句柄即是后续read()和recv()的输入参数。如果请求队列当前没有请求，则accept() 将进入阻塞状态直到有请求进入队列。
* */
public class BIO {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(888);
			while (true){  //等待客户端连接
				Socket socket = serverSocket.accept(); //阻塞等待连接
				System.out.println("已连接：" + socket.getPort());

				Scanner input = new Scanner(socket.getInputStream());
				while (true){ //不断读写客户端的请求
					// 阻塞，占用当前线程资源，合适吗？ 如果客户端没有数据传过来，当前线程一直等到客户端数据的到来
					String request = input.nextLine(); // 等待客户端数据，占用线程，不能做别的操作
					if ("quit".equals(request)){
						break;
					}
					System.out.println(String.format("From %s : %s",socket.getRemoteSocketAddress(), request));
					String response = String.format("From %s : %s", socket.getLocalSocketAddress(), request);
					socket.getOutputStream().write(response.getBytes());
				}
			}
		}catch (IOException es){
			es.printStackTrace();
		}
	}
}
