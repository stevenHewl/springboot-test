package volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/*
* 原子变量具备如下特点：
有volatile保证内存可见性。
用CAS算法保证原子性。
* */
public class atomicTest {
	public static void main(String[] args) {
		AtomicDemo atomicDemo = new AtomicDemo();
		for (int x = 0;x < 10; x++){
			new Thread(atomicDemo).start();
		}
	}
}

class AtomicDemo implements Runnable{
	// i++ 不具有原子操作性
	/*
	private int i = 0;
	public int getI(){
		return i++;
	}
	*/

	AtomicInteger i = new AtomicInteger();
	public int getI(){
		return i.getAndIncrement(); ///原子变量具有原子操作性
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getI());
	}
}