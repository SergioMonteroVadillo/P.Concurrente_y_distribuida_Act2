package Semaforos;

/****************************************************
 * Asignatura: Programación concurrente y distribuida
 * Profesor: José Delgado Pérez
 * Alumno: Sergio Montero Vadillo
 * DNI: 53426672-H 
 * Actividad : Actividad 2 Parte 1 / Semaforos
 * Última modificación: 06/2/2023
 ****************************************************/
public class Program {
	
	public static final int reserva = 10;	//Número de procesos que solicitan n unidades de recursos
	public static final int liberador = 5;	//Número de procesos que liberan recursos reservados

	public static void main(String[] args) {
		
		for (int i = 0; i < reserva; i++) {
			new Booked();
		}
		
		for (int i = 0; i < liberador; i++) {
			new Freed();
		}
	}
}
