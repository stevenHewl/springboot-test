package volatileTest;


/*
* volatile关键字保证了操作的可见性，但是volatile能保证对变量的操作是原子性吗？ 能
* */
public class volatileTest2 {

	public volatile int inc = 0;

	public void increase() {
		inc++;
	}

	public static void main(String[] args) {
		final volatileTest2 test = new volatileTest2();
		for(int i=0;i<2;i++){
			new Thread(){
				public void run() {
					for(int j=0;j<10;j++)
						test.increase();
				};
			}.start();
		}

		while(Thread.activeCount()>1)  //保证前面的线程都执行完
			Thread.yield();

		System.out.println(test.inc);
	}
}
