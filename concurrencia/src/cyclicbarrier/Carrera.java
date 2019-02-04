package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Carrera implements Runnable {
	
	private int id;
	private static int participantes;
	private double inicio, total;
	private Random rand = new Random(System.nanoTime());
	
	private static double[][] tiempos = new double[participantes][4];
	
	private static CyclicBarrier barrera = new CyclicBarrier(participantes);
	
	public Carrera(int id, int participantes) {
		this.id = id;
		this.participantes = participantes;
	}

	@Override
	public void run() {
		this.inicio = System.nanoTime();
		
		try {
			Thread.sleep(rand.nextInt()+100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.total = System.nanoTime() - this.inicio;
		tiempos[id][0] = total;
		
		try {
			barrera.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public static double[][] getTiempos() {
		return tiempos;
	}

}
