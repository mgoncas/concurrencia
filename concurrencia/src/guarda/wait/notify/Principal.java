package guarda.wait.notify;

import java.util.Random;

/**
 * 
 * @author mgonzcas Implementamos runnable cuando necesitamos herencia múltiple
 */
public class Principal implements Runnable {

	private int id;
	private static int cont = 0;
	private static Random cerrojo = new Random();
	
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
		
		System.out.println("soy el hilo principal");
	}


	@Override
	public void run() {
		synchronized (cerrojo) {
			// mientras que no sea su turno, lo echamos a dormir
			while (id != cont) {
				try {
					cerrojo.wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("soy el hilo: "+id);
			cont++;
			// volvemos a despertar todos los hilos
			cerrojo.notifyAll();
		}
	}

}
