package hilos.dinamico;

import java.util.Random;

// multiplicar por 10 todos los elementos de una matriz
// de forma concurrente y medir el tiempo

public class Principal extends Thread {
	
	private static int tam = 800;
	private static int[][] matriz = new int[tam][tam];
	private int inicio;
	private int fin;
	
	public Principal(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run() {
		for(int i = inicio; i<fin; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = matriz[i][j]*10;
			}
		}
	}

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		int numNucleos = runtime.availableProcessors();
		
		Random rand = new Random(System.nanoTime());
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
		Thread[] hilos = new Thread[numNucleos];
		int rango = tam/numNucleos;
		int start = 0;
		int finish = rango;
		
		for(int i = 0; i<numNucleos; i++) {
			if (i != numNucleos-1) {
				hilos[i] = new Principal(start, finish);
				hilos[i].start();
				System.out.println("hilo "+ i + " start: "+start + " finish: "+finish);
				start = finish;
				finish = finish + rango;
			} else {
				hilos[i] = new Principal(start, tam);
				hilos[i].start();
				System.out.println("hilo "+ i + " start: "+start + " finish: "+tam);
			}
			
		}
		
		
		try {
			for (int i = 0; i < numNucleos; i++) {
				hilos[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pintaMatriz();
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

}
