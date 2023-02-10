import java.util.Random;

public class Trafico extends Thread{ //Clase que hereda de la clase hilo, para generar hilos concurrentes
	
	private enum STATE{RUNNING, STOPPING};	// Posibles estados de los hilos
	private STATE state;					
	private String lugarTrafico;	
	private MonitorPuente monitor;
	
	public Trafico (String n, MonitorPuente m) {	//Constructor que inicializa como estado parado 
		lugarTrafico = n;
		state = STATE.STOPPING;
		monitor = m;
		start();	//Inicia hilo concurrente
	}
	
	private void running () {		//Función que permite el paso de tráfico, cuando el estado está en movimiento
		
		Random rdm = new Random();		
		int totalCoches = rdm.nextInt(7 - 1 + 1) + 1;		//Número aleatorio de coches que van a cruzar
		System.out.println(lugarTrafico + ", empieza a cruzar. Y cruzan " + totalCoches + " coches");
		
		for (int i = 0; i < totalCoches; i++) {	//Bucle que hace cruzar a cada coche y tarda cada coche un tiempo aleatorio 
			try {
				int tCoches = rdm.nextInt(500 - 350 + 1) + 350;
				sleep(tCoches);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}		
		}
		
		System.out.println(lugarTrafico + ", ha dejado de cruzar.");
		monitor.liberarPuente();	//Cuando terminan de cruzar,informa al monitor de que ha liberado el puente 
		state = STATE.STOPPING;		//Se cambia a estado parado
	}
	
	private void stopping() {
		System.out.println(lugarTrafico + ", se espera a generar cola de coches y a que se libere el puente.");
		monitor.ocuparPuente();		//Cuando se le permite el paso, informa al monitor que va a ocupar el puente
		state = STATE.RUNNING;		//Cambia a estado en movimiento
	}
	
	@Override
	public void run() {
		while (true) {		//Bucle infinito que analiza el estado del tráfico
			switch (state) {
			case STOPPING:
				stopping();
				break;
			case RUNNING:
				running();
				break;
			}
		}
	}

}