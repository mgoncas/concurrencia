package tiposatomicos;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic implements Runnable{

	private static AtomicInteger cont = new AtomicInteger();
	
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			cont.getAndIncrement();
		}
	}
	
	public static int getCont() {
		return cont.get();
	}

}
