package cyclicbarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * En una carrera ciclista con 100 participantes hay 3 etapas, antes de comenzar una etapa hay que esperar
 * a que lleguen todos los participantes, una vez que lleguen se dará la salida a la siguiente etapa.
 * El ganador de la prueba, será el que mejor tiempo haga en total y también se requiere saber el ganador
 * de cada una de las etapas.
 */
public class Principal {

	public static void main(String[] args) {
		int participantes = 100;
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i=0; i< participantes; i++) {
			Runnable runnable = new Carrera(i, participantes);
			pool.execute(runnable);
		}
		
		pool.shutdown();
		while(!pool.isTerminated());
		
		double[][] tiempos = Carrera.getTiempos();
		

	}

}
