import java.util.Random;

public class Freed extends Thread{	//Clase que hereda de la clase hilo, para generar hilos concurrentes
	
	private static int freedCount = 0;	//Variables que establecen el número de liberador que libera recursos
	private int freedId;
	
	public Freed() {	//Constructor que asigna Id del liberador e inicializa hilo concurrente
		
		freedId = ++freedCount;
		start();
	}
	
	private void freed() {	//Función que libera buffer de reserva de recursos y establece tiempo de acción
	Random rdmNum = new Random();
	int sleepTime = rdmNum.nextInt(2500 - 25 + 1) + 25;
	
	try {
		sleep(sleepTime);	//Tiempo aleatorio para realizar liberación
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	int consumedNumber = Buffer.getStore().poll();	//Se extrae de buffer la primera reserva de la cola y libera recursos
	
	System.out.println("El liberador " + freedId + ": Liebera " + consumedNumber + " recursos.");
	}
	
	@Override
	public void run() {		//Función que sobrescribe la función run de la clase thread, generando hilo concurrente para el proceso
		
		while(true) {	//Bucle infinito para que se esté liberando constantemente
			
			if (Buffer.getStore().size() == 0) { 	//Si el buffer está vacío imprime mensaje
				System.out.println("El liberador " + freedId + ": El buffer esta vacio, esperando a que se genere alguna reserva de recursos.");
			}
			
			try {
				Buffer.getsNoVacio().acquire();	//Semáforo que nos indica si el buffer no está vacio, si esta vacío espera a que se ocupe por lo menos un espacio
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			freed();	//Cuando el buffer tenga al menos una reserva, se realizará la liberación de recursos
			
			Buffer.getsNoLleno().release(); //Se inidica al semáforo de buffer no lleno, que se ha liberado una reserva al buffer
		}
	}
	

}
