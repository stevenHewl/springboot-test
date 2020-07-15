package rpcClient;

import rpc.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// 远程网络IO调用
public class RpcNetTransport {
	private String host;
	private Integer port;

	public RpcNetTransport(String host, Integer port){
		this.host = host;
		this.port = port;
	}

	public Object send(RpcRequest request){
		try(Socket socket = new Socket(this.host, this.port);
		    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){
			outputStream.writeObject(request);
			outputStream.flush();
			return inputStream.readObject();
		}catch (Exception es){
			es.printStackTrace();
		}
		return null;
	}
}
