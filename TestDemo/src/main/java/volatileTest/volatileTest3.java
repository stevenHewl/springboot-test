package volatileTest;

/*
* volatile关键字：当多个线程操作共享数据时，可以保证内存中的数据可见。用这个关键字修饰共享数据，就会及时的把线程缓存中的数据刷新到主存中去，
* 就是直接操作主存中的数据。所以在不使用锁的情况下，可以使用volatile
*
*
* */
public class volatileTest3 {
	public static void main(String[] args) {
		ThreadDemo threadDemo = new ThreadDemo();
		Thread thread = new Thread(threadDemo);
		thread.start();
		while (true){
			if (threadDemo.flag){
				System.out.println("主线程读取到的flag = " + threadDemo.flag);
				break;
			}
		}
	}
}

class ThreadDemo implements Runnable{ //这个线程是用来修改flag的值的
	public volatile  boolean flag = false;
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = true;
		System.out.println("ThreadDemo线程修改后的flag = " + flag);
	}
}