package runnable.synchroniz;

/**
 * 
 * @author mgonzcas Implementamos runnable cuando necesitamos herencia múltiple
 */
public class Principal implements Runnable {

	private static int cont = 0;
	private static Object object = new Object();

	public static void main(String[] args) {
		/*
		 * Runnable runnable = new Principal(); Thread hilo = new Thread(runnable);
		 * hilo.start();
		 * 
		 * try { hilo.join(); } catch (Exception e) { }
		 */

		// desde un vector
		Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new Principal());
			hilos[i].start();
		}

		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
			}
		}
		System.out.println(cont);
	}

	@Override
	public void run() {
		System.out.println("corro hilo");
		/**
		 * el object es el cerrojo y debe ser estático
		 */
		synchronized (object) {
			for (int i = 0; i < 20000; i++) {
				cont++;
			}
		}
	}

}
