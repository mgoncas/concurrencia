package reentrantlock;

public class Principal {

	public static void main(String[] args) {
		Thread hilo = new Thread(new Reentrant());
		Thread hilo2 = new Thread(new Reentrant());
		hilo.start();
		hilo2.start();
		try {
			hilo.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Reentrant.getCont());
	}

}
