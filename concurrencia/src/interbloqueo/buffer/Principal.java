package interbloqueo.buffer;

import java.util.Random;

/**
 * 
 * @author mgonzcas 
 * 
 */
public class Principal implements Runnable {

	private int id;
	private static int cont = 0;
	private int contPrivate = 0;
	private static Random cerrojoa = new Random();
	
	public Principal(int id) {
		this.id = id;
	}

	public static void main(String[] args) {
		// desde un vector
		Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new Principal(i));
			hilos[i].start();
		}

		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
			}
		}
		System.out.println(cont);
		System.out.println("soy el hilo principal");
	}


	@Override
	public void run() {
		for (int i = 0; i < 20000; i++) {
			contPrivate++;
		}
		synchronized (cerrojoa) {
			cont = cont+contPrivate;
		}
	}


}
