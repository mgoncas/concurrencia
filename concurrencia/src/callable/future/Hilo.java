package callable.future;

import java.util.concurrent.Callable;

public class Hilo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int cont =0;
		for (int i = 0; i < 100000; i++) {
			cont++;
		}
		return cont;
	}


}
