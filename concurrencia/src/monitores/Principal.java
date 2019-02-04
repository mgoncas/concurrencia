package monitores;

public class Principal implements Runnable{
	
	private static Monitor monitor = new Monitor();
	private int id;
	
	public Principal(int id) {
		this.id = id;
	}

	public static void main(String[] args) {
		Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
		for(int i= 0; i<hilos.length ; i++) {
			hilos[i] = new Thread(new Principal(i));
			hilos[i].start();
		}
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		System.out.println(monitor.inc(20000));
		monitor.ordenar(id);
	}

}
