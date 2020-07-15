package com.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 非阻塞 IO
 * 非阻塞IO ，IO请求时加上O_NONBLOCK一类的标志位，立刻返回，IO没有就绪会返回错误，需要请求进程主动轮询不断发IO请求直到返回正确
 * IO复用同非阻塞IO本质一样，不过利用了新的select系统调用，由内核来负责本来是请求进程该做的轮询操作。
 * 看似比非阻塞IO还多了一个系统调用开销，不过因为可以支持多路IO，才算提高了效率
 * */
public class NIO {
	public static void main(String[] args) {
		// 服务器监听一个端口，等待多个客户端的连接数据交互处理   只有一个main线程
		try {
			// 类似ServerSocket  根据操作系统 获取相应的 channel
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // 获取服务端的channel
			serverSocketChannel.configureBlocking(false); // 服务端channel设置成非阻塞模式
			serverSocketChannel.bind(new InetSocketAddress(999));  // 服务端channel监听999端口

			/*
			* Selector 一般称 为选择器 ，当然你也可以翻译为 多路复用器 。它是Java NIO核心组件中的一个，用于检查一个或多个NIO Channel（通道）
			* 的状态是否处于可读、可写。如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。
			* */
			Selector selector = Selector.open();  // 根据 OS 获取相应的 Selector，这个

			//一个SelectionKey键表示了一个特定的通道对象和一个特定的选择器对象之间的注册关系。
			SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  // 将channel注册到selector

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			SocketChannel clientChannel;
			while (true){
				/*
				* select()方法返回的int值表示有多少通道已经就绪,是自上次调用select()方法后有多少通道变成就绪状态。之前在select（）调用时进入
				* 就绪的通道不会在本次调用中被记入，而在前一次select（）调用进入就绪但现在已经不在处于就绪的通道也不会被记入
				* */
				int select = selector.select(); // 返回的就绪的Channel描述符，也就是得到事件就绪的Channel个数
				if (select == 0){
					continue;
				}
				// 获取Selector中的channel集合
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator(); // 迭代器
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isAcceptable()) {
						clientChannel = ((ServerSocketChannel)key.channel()).accept();
						System.out.println("远程已连接：" + clientChannel.getRemoteAddress());
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ); // 更改channel的状态
					}
					if (key.isReadable()) {
						clientChannel = (SocketChannel) key.channel();
						clientChannel.read(buffer); // 通过 buffer 缓冲区 读取，写数据
						String request = new String(buffer.array()).trim();
						buffer.clear();
						System.out.println(String.format("From %s : %s", clientChannel.getRemoteAddress(), request));
						String response = String.format("From %s : %s", clientChannel.getLocalAddress(), request);
						clientChannel.write(ByteBuffer.wrap(response.getBytes()));
					}
					iterator.remove();
				}
			}
		}catch (Exception es){
			es.printStackTrace();
		}

	}
}
