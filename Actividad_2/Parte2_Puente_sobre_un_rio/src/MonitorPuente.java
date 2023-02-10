
public class MonitorPuente {
	
	private boolean puenteLibre = true;
	
	public MonitorPuente () {		
	}
	
	public synchronized void ocuparPuente () {
		
		while (!puenteLibre) {	//Bucle infinito que espera a que el puente este libre para ocuparlo
			
			try {
				wait();		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		puenteLibre = false;	//Pone el monitor como puente ocupado
	}
	
	public synchronized void liberarPuente () {
		puenteLibre = true;		//Pone el monitor como puente libre
		notify();	//Informa a hilo a la espera que el puente está libre
	}
}
