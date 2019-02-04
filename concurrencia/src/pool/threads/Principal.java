package pool.threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Para lanzar muchos hilos pool de thread es ideal
public class Principal implements Runnable {
	
	private static int tam = 20000;
	private static int[][] matriz = new int[tam][tam];
	private int fila;
	
	public Principal(int fila) {
		this.fila = fila;
	}

	public static void main(String[] args) {
		double tiempoInicio, tiempoFinal;
		Random rand = new Random(System.nanoTime());
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
//		pintaMatriz();
		
		System.out.println();
		
		tiempoInicio = System.nanoTime(); // hora en nanosegundos
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i= 0; i<tam; i++) {
			pool.execute(new Principal(i));
		}
		
		pool.shutdown();
		
		while (!pool.isTerminated());
		
		/**
		Thread[] hilos = new Thread[tam];
		
		for(int i=0; i<hilos.length; i++) {
			hilos[i] = new Thread(new Principal(i));
			hilos[i].start();
		}
		
		try {
			for(int i=0; i<hilos.length; i++) {
				hilos[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		**/
		
		tiempoFinal = System.nanoTime() - tiempoInicio;
		
		
//		pintaMatriz();
		System.out.println();
		System.out.println((tiempoFinal/1000000) + " milisegundos");
	}
	
	private static void pintaMatriz() {
		for(int i = 0; i<matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	@Override
	public void run() {
		for (int i = 0; i < tam; i++) {
			matriz[fila][i] *= 10;
		}
	}

}
