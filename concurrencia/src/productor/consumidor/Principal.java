package productor.consumidor;

public class Principal implements Runnable{
	
	private boolean consumidor;
	private static int tarta = 0;
	private static Object lock = new Object();
	
	public Principal(boolean consumidor) {
		this.consumidor = consumidor;
	}

	public static void main(String[] args) {
		int numHilos = 11;
		Thread[] hilos = new Thread[numHilos];
		for(int i= 0; i<hilos.length ; i++) {
			Runnable runnable = null;
			if (i!= 0) {
				runnable = new Principal(true);
			} else {
				runnable = new Principal(false);
			}
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			if (consumidor) {
				consumiendo();
			} else {
				cocinando();
			}
		}
		
	}

	private void consumiendo() {
		synchronized (lock) {
			if (tarta > 0) {
				tarta--;
				System.out.println("soy un consumidor y quedan "+tarta);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} else {
				lock.notifyAll();
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void cocinando() {
		synchronized (lock) {
			if (tarta ==0) {
				tarta = 10;
				System.out.println("soy el cocinero y quedan "+tarta);
				lock.notifyAll();
			}
			try {
				lock.wait();
			} catch (Exception e) {
			}
		}
	}

}
