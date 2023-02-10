import java.util.Random;

public class Booked extends Thread{	//Clase que hereda de la clase hilo, para generar hilos concurrentes 
	
	private static int bookedCount = 0;	//Variables que establecen el número de reserva que solicita recursos
	private int bookedId;
	
	public Booked() {		//Constructor que asigna Id de reserva e inicializa hilo concurrente
		bookedId = ++bookedCount;
		start();
	}
	
	private void booked() {	//Función que genera número aleatorio de recursos solicitados, establece tiempo de acción y añade a buffer
		
		Random rdmNum = new Random();
		int numP = rdmNum.nextInt(150);
		int sleepTime = rdmNum.nextInt(1000 - 500 + 1) + 500;
		
		try {
			sleep(sleepTime);		//Tiempo aleatorio para realizar reserva
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("La reserva " + bookedId + ": Solicita " + numP + " recursos.");
		
		Buffer.getStore().add(numP); //Añadimos reserva al buffer
	}
	
	@Override
	public void run() {	//Función que sobrescribe la función run de la clase thread, generando un hilo concurrente para el proceso 
		
		while(true) {	//Bucle infinito para que se este reservando constantemente 
			
			if (Buffer.getStore().size() == Buffer.bSize) {	//Si el buffer está lleno imprime mensaje
				System.out.println("La reserva " + bookedId + ": El buffer esta lleno, esperando a que se liberen recursos.");
			}
			
			try {
				Buffer.getsNoLleno().acquire();		//Semáforo que nos indica si el buffer no está lleno, si está lleno espera a que se libere por lo menos un espacio
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			booked();		//Cuando el buffer tenga espacio, se realizará reserva de recursos
			
			Buffer.getsNoVacio().release();		//Se inidica al semáforo de buffer no vacio, que se ha añadido una reserva al buffer
		}
	}
}
