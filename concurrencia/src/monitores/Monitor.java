package monitores;

public class Monitor {

	private int cont = 0;
	private int order = 0;
	
	public synchronized int inc(int parametro) {
		for(int i = 0; i< parametro; i++) {
			cont++;
		}
		return cont;
	}
	
	public synchronized void ordenar(int id) {
		while(id != order) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("soy el hilo "+id);
		order++;
		notifyAll();
	}
}
