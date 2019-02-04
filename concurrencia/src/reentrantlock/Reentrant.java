package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Reentrant implements Runnable{
	
	private static ReentrantLock cerrojo = new ReentrantLock();
	private static int cont = 0;

	@Override
	public void run() {
		cerrojo.lock();
		for (int i = 0; i < 1000000; i++) {
			cont++;
		}
		cerrojo.unlock();
	}
	
	public static int getCont() {
		return cont;
	}
	
}
