package medir.concurrencia.matriz;

import java.util.Random;

// multiplicar por 10 todos los elementos de una matriz
// de forma concurrente y medir el tiempo

public class Principal extends Thread {
	
	private static int tam = 4;
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
		double tiempoInicio, tiempoFinal;
		Random rand = new Random(System.nanoTime());
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
		pintaMatriz();
		
		System.out.println();
		
		tiempoInicio = System.nanoTime(); // hora en nanosegundos
		
		Principal h1 = new Principal(0,2);
		Principal h2 = new Principal(2,4);
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tiempoFinal = System.nanoTime() - tiempoInicio;
		
		System.out.println((tiempoFinal/1000000) + " milisegundos");
		
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
