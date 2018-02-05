package javacode.structure.stack;
/**
 * 加入的时候，抢锁；抢到，校验是否满size 是否大于 length了？满了等待，未满加入，size+1;
 * 
 * 问题：
 * 	size是不确定的
 * 	notifyAll()不确定唤醒哪个
 * 
 * @author Administrator
 *
 */
public class StackObject {
	
	private final int[] _data;
	
	public StackObject(int limit){
		_data =   new int[limit];
	}

	private final Object lock = new Object();
	
	private int size = 0;
	
	public void push (int data){
		synchronized (lock) {
			//
			while (size >= _data.length) {
				try {
					//锁在对象上，本线程调用wai()方法，释放锁，进入等待池。
					System.out.println("棧大小="+size+"加入"+data);
					System.out.println(Thread.currentThread().getName()+"进入等待队列");
					System.out.println("--------------------------------------------");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("--------------------------------------------");
			System.out.println("棧大小="+size+"加入"+data);
			System.out.println("--------------------------------------------");
			_data[size] = data;
			++size;
			lock.notifyAll();//通知等待队列的线程可以竞争锁了。
		}
	}
	/**
	 * 弹出:
	 * 	1、检查是否空了，空了就等待
	 * 		
	 * @return
	 */
	public int pop(){
		int v;
		synchronized (lock) {
			while(size ==0){
				System.out.println("因为空了"+Thread.currentThread().getName()+"进入等待队列");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			v= _data[size-1];
			--size;
			lock.notifyAll();
			return v;
		}
		
	}
	
}
