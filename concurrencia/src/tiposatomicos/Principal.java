package tiposatomicos;

public class Principal {

	public static void main(String[] args) {
		Thread hilo = new Thread(new Atomic());
		Thread hilo2 = new Thread(new Atomic());
		hilo.start();
		hilo2.start();
		try {
			hilo.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Atomic.getCont());
	}

}
