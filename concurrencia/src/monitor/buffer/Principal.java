package monitor.buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal implements Runnable{
	
	private int id;
	private static Buffer buf = new Buffer();
	
	public Principal(int id) {
		this.id = id;
	}

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		
		ExecutorService poolLector = Executors.newFixedThreadPool(nNucleos);
		for (int i = 0; i < nNucleos; i++) {
			Runnable runnable = new Principal(0);
			poolLector.execute(runnable);
		}
		
		poolLector.shutdown();
		
		ExecutorService poolEscritor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 2; i++) {
			Runnable runnable = new Principal(1);
			poolEscritor.execute(runnable);
		}
		poolEscritor.shutdown();
		
		while(!poolLector.isTerminated());
	}

	@Override
	public void run() {
		if (id == 0) {
			int elemento;
			while(true) {
				elemento = buf.leer();
				if (elemento != -1) {
					System.out.println("El elemento extraido del buffer es: "+elemento);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			while(true) {
				buf.escribir();
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
