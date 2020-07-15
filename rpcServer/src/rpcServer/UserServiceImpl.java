package rpcServer;

import rpc.IUserService;


public class UserServiceImpl implements IUserService {

	@Override
	public String doSave(String name){
		System.out.println("服务器端数据存储成功：" + name);
		return "服务端口接口方法执行完毕！";
	}
}
