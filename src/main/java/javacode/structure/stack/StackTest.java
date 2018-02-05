package javacode.structure.stack;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.tomcat.util.threads.TaskThread;

public class StackTest {
	
	public static void main(String[] args) {
		
		StackObject stack = new StackObject(10);
		
		Runnable stackadd = ()->{
			stack.push(2);
		};
		
		ExecutorService task = Executors.newCachedThreadPool();
		for (int i = 0; i < 30; i++) {
			//task.execute(stackadd);
			if(i<15){
				task.execute(new Runnable() {				
					@Override
					public void run() {
						int data = new Random().nextInt(100-0)+0;
						stack.push(data);		
					}
				});
			}else{
				task.execute(new Runnable() {
					
					@Override
					public void run() {
						System.out.println(stack.pop());
						
					}
				});
			}
			
		}
		
	}

}
