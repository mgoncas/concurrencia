package indeterminacion;

/**
 * 
 * @author mgonzcas
 *	Indeterminismo
 *	Cuando dos hilos estan escribiendo a la vez en una variable compartida
 *	el valor de la variable cont es indeterminado
 */
public class Principal extends Thread{
	
	private static int cont = 0;
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			cont++;
		}
	}

	public static void main(String[] args) {
		Principal[] vec = new Principal[1000];
		for (int i = 0; i < vec.length; i++) {
			vec[i] = new Principal();
			vec[i].start();
		}
		
		for (int i = 0; i < vec.length; i++) {
			try {
				vec[i].join();
			} catch (InterruptedException e) {}
		}
		
		System.out.println(cont);
	}

}
