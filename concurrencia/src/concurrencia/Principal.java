package concurrencia;

// start() lanza el hilo
// los hilos no se tienen que ejecutar cuando se lanzan
// no sabemos el orden de ejecucion de los hilos
public class Principal extends Thread{
	
	private int id;
	
	public Principal(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("soy el hilo "+ id);
	}

	public static void main(String[] args) {
		Principal p1 = new Principal(1);
		Principal p2 = new Principal(2);
		Principal p3 = new Principal(3);
		
		p1.start(); // se crea un nuevo hilo
		p2.start(); // se crea un nuevo hilo
		p3.start(); // se crea un nuevo hilo
		// continua al hilo principal
		System.out.println("soy el hilo principal");
	}

}
