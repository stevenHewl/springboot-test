package test;

import singleton.lazy.LazySingleton;

public class ExecutorThread implements Runnable {

	public void run(){
		LazySingleton instance = LazySingleton.getInstance();
		System.out.println(Thread.currentThread().getName()+":"+instance);
	}
}
