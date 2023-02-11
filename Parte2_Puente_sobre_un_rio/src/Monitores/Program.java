package Monitores;
/****************************************************
 * Asignatura: Programación concurrente y distribuida
 * Profesor: José Delgado Pérez
 * Alumno: Sergio Montero Vadillo
 * DNI: 53426672-H 
 * Actividad : Actividad 2 Parte 2 / Monitores
 * Última modificación: 06/2/2023
 ****************************************************/
public class Program {

	public static void main(String[] args) {
		
		MonitorPuente monitor = new MonitorPuente();
		
		// Se crean objetos con diferentes direcciones de circulación 
		new Trafico("Trafico norte:", monitor);
		new Trafico("Trafico sur:  ", monitor);
	}

}
