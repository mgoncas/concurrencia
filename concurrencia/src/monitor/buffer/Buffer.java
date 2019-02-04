package monitor.buffer;

import java.util.Random;
import java.util.Vector;

public class Buffer {
	private Random random = new Random(System.nanoTime());

	private int tam = 1;
	private int pos = -1; // -1 significa que está vacío
	private Vector<Integer> cola = new Vector<Integer>();

	public synchronized int leer() {
		int elemento = -1;
		while (pos < 0) {
			try {
				System.out.println("El vector está vacío y el consumidor se va a dormir");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elemento = cola.get(pos);
		cola.remove(pos);
		pos--;
		return elemento;
	}

	public synchronized void escribir() {
		pos++;
		if (pos >= tam) {
			System.out.println("El vector está lleno");
			pos--;
		} else {
			cola.add(generar());
			notifyAll();
		}
	}

	public synchronized int generar() {
		return random.nextInt(10);
	}

}
