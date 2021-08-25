package arsw.threads;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	static Semaphore sem = new Semaphore(1);
	private boolean pausa = false;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size() && !pausa) {
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			if (paso == carril.size()) {
				sem.acquire();
				carril.finish();
				int ubicacion=regl.getUltimaPosicionAlcanzada();
				regl.setUltimaPosicionAlcanzada(ubicacion+1);
				sem.release();
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1){
					regl.setGanador(this.getName());
				}
				
			}
		}
	}


	@Override
	public void run() {
		while(!pausa) {
			try {
				corra();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pausa(){
		pausa = true;
	}

	public void seguir() throws InterruptedException {
		pausa = false;
		corra();
	}
}
