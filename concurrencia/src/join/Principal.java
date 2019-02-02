package join;

public class Principal extends Thread {
	
	private int id;
	
	public Principal(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("lanzando hilo "+this.id);
	}

	public static void main(String[] args) {
		Principal[] vec = new Principal[5];
		for(int i = 0; i<vec.length; i++) {
			vec[i] = new Principal(i+1);
			vec[i].start();
			/** si aquí pusieramos el join() del hilo
			 * ejecutaría uno detrás del otro porque el principal
			 * esperaría a seguir ejecutando el bucle para abrir 
			 * el siguiente hilo
			 */
		}
		try {
			// join() sirve para que el hilo principal espere al hilo
			// que se marca con el join
			for(int i = 0; i<vec.length; i++) {
				vec[i].join();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("soy el hilo principal");
	}

}
