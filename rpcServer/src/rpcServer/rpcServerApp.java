package rpcServer;


import rpc.IUserService;

public class rpcServerApp {
	public static void main(String[] args) {
		 IUserService userService = new UserServiceImpl();
		 RpcProxyServer rpcProxyServer = new RpcProxyServer();
		 // 发布IUserService服务到8080端口上
		 rpcProxyServer.publish(userService, 8080);
	}
}
