import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Buffer {
	
	private static ConcurrentLinkedQueue<Integer> store = new ConcurrentLinkedQueue<Integer>(); //Cola concurrente
	public static final int bSize = 5;				//Tamaño de buffer, el cual es infinito. Valor máximo de tipo entero
	private static Semaphore sNoVacio = new Semaphore(0, true);  	//Semáforo si buffer esta lleno
	private static Semaphore sNoLleno = new Semaphore(bSize, true); //Semáforo si buffer esta vacio
	
	public static Queue<Integer> getStore() {
		return store;
	}
	
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}
	
	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}
}
