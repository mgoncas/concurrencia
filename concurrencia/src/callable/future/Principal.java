package callable.future;

import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Principal {

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		int cont = 0;
		
		ExecutorService pool = Executors.newFixedThreadPool(nNucleos);
		Vector<Future<Integer>> vec = new Vector<>();
		
		for (int i = 0; i < nNucleos; i++) {
//			Future<Integer> future = Executors.
			
		}
	}

}
