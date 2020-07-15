import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行：
/* https://www.liaoxuefeng.com/wiki/1252599548343744/1306580767211554
优势
   多线程返回执行结果是很有用的一个特性，因为多线程相比单线程更难、更复杂的一个重要原因就是因为多线程充满着未知性，某条线程是否执行了？某条线程执行了多久？
   某条线程执行的时候我们期望的数据是否已经赋值完毕？无法得知，我们能做的只是等待这条多线程的任务执行完毕而已。
   而Callable+Future/FutureTask却可以获取多线程运行的结果，可以在等待时间太长没获取到需要的数据的情况下取消该线程的任务，真的是非常有用。
 */
public class threadJoin {
    public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<String>(new MyCallable("测试future.get()"));
        try {
            System.out.println("this is main thread begin");
            new Thread(future).start();
            System.out.println(future.isDone());
            System.out.println("call future.get() main thread is blocked"); // 调用future.get()方法会阻塞主线程，如果不调用不会阻塞。
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            FutureTask<String> future2 = new FutureTask<String>(new MyCallable("测试thread.join()"));
            System.out.println("=======================================================");
            System.out.println("this is main thread begin");
            Thread thread = new Thread(future2);
            thread.start();
            System.out.println("call thread.join(), 等待thread执行完，再执行主线程"); // 调用future.get()方法会阻塞主线程，如果不调用不会阻塞。
            thread.join();
            System.out.println(future2.isDone());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("start new thread!");
    }
}

class MyCallable implements Callable<String> {
    String param;
    public MyCallable(String param){
        this.param = param;
    }
    public String call() throws Exception{
        System.out.println("start new thread!, param:" + param);
        Thread.sleep(6000);
        return "ok";
    }
}