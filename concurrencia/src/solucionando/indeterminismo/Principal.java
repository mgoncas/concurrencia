package solucionando.indeterminismo;

import java.util.Random;

/**
 * 
 * @author mgonzcas
 * realizar la multiplicacion de todos los elementos de un vector 
 * de enteros por 10
 * 
 */
public class Principal extends Thread{
	
	private static int tam = 8;
	private static int[] vec = new int[tam];
	
	private int ini, fin;
	
	public Principal(int ini, int fin) {
		this.ini = ini;
		this.fin = fin;
	}
	
	public void run() {
		for (int i = ini; i < fin; i++) {
			vec[i] = vec[i]*10;
		}
	}

	public static void main(String[] args) {
		Random rand = new Random(System.nanoTime());
		
		for (int i = 0; i < vec.length; i++) {
			vec[i] = rand.nextInt(10);
		}
		
		Principal p1 = new Principal(0,4);
		Principal p2 = new Principal(5,8);
		
		p1.start();
		p2.start();
		
		try {
			p1.join();
			p2.join();
		} catch (Exception e) {
		}
		
		for (int i = 0; i < vec.length; i++) {
			System.out.println(vec[i]+" ");
		}
		
	}

}
