package singleton.register;


/*
* 枚举天生线程安全，在线程访问前，先把对象缓存好，放在一个容器中
* 枚举可归纳到恶汉式单例中
*
* */
public enum EnumSingleton {
	Instance;

	private Object data;
	public Object getData(){
		return data;
	}

	public void setData(Object data){
		this.data = data;
	}

	public static EnumSingleton getInstance(){
		return Instance;
	}

}
