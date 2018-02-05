package javacode.structure.stack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StackCondition {
	private final int[] _data;
    private int size = 0;
 
    public StackCondition(int limit) {
        _data = new int[limit];
    }
    
    private ReentrantLock lock =  new ReentrantLock();
    
    //空队列
    private final Condition lock_empty = lock.newCondition();
    //满队列
    private final Condition lock_full = lock.newCondition();
    
    public void push(int data){
    	lock.lock();
    	while(size > _data.length){
    		try {
				lock_full.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	_data[size] =data;
    	++size;
    	lock_empty.signalAll();
    	lock.unlock();
    }
    
    public int pop() {
        int v;
         
        lock.lock();
        while (size == 0) {
            try {
                lock_empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 
        v = _data[size - 1];
        --size;
        lock_full.signalAll();      
        lock.unlock();
         
        return v;
    }   

}
