package test;

public class LazySingletonTest {
	public static void main(String[] args) {
		Thread t0 = new Thread(new ExecutorThread());
		Thread t1 = new Thread(new ExecutorThread());

		t0.start();
		t1.start();
	}
}
