package controller;

import java.util.Scanner;

import model.data_structures.Node;
import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	private int trim;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = null;
	}
		
	public void run() throws Exception 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		double val1;
		double val2;
		int val3;
		int val4;
		int val5;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					System.out.println("--------- \nCrear MVCModelo \nDar trimestre que desea cargar: ");
				    trim = lector.nextInt();
				    modelo = new MVCModelo(trim); 
					System.out.println("MVCModelo creado");					
					break;

				case 2: 
					System.out.println("--------- \\nCrear MVCModelo \\nDar trimestre que desea cargar:");
					trim = lector.nextInt();
				    modelo = new MVCModelo(trim); 
					System.out.println("--------- \\nDar número para devolver las N letras más frecuentes: ");	
					int valor = lector.nextInt();
					modelo.darNLetrasMásFrecuentes(valor);
					break;
				case 3:
					modelo = new MVCModelo(1);
					System.out.println("--------- \\nDar latitud a buscar:");
					val1 = lector.nextDouble();
					System.out.println("--------- \\nDar longitud a buscar:");
					val2 = lector.nextDouble();
					Queue<String> cola0 = modelo.BuscarNodosDelimitacionGeografica(val1, val2);
					Node actual0 = cola0.darPrimerNodo();
					System.out.println("El tamaño fue de: "+ cola0.size());
					while(actual0 != null)
					{
						String cadena = (String) actual0.darItem();
						System.out.println(cadena);
						actual0 = actual0.darSiguiente();
					}
				case 4:
					modelo = new MVCModelo(1); 
					System.out.println("--------- \\nDar límite inferior de la búsqueda:");
					val1 = lector.nextDouble();
					System.out.println("--------- \\nDar límite superior de la búsqueda:");
					val2 = lector.nextDouble();
					System.out.println("--------- \\nDar cantidad N a buscar:");
					val3 = lector.nextInt();
					Queue<String> cola = modelo.BuscarEnUnRangoYPrimerTrimestreDesviacion2018(val1, val2, val3);
					Node actual = cola.darPrimerNodo();
					while(actual != null)
					{
						String cadena = (String) actual.darItem();
						System.out.println(cadena);
						actual = actual.darSiguiente();
					}
				case 5:
					modelo = new MVCModelo(1);
					System.out.println("--------- \\nDar las N zonas más al norte a buscar:");
					val3 = lector.nextInt();
					modelo.darNZonasMásAlNorte(val3);
				case 6:
					modelo = new MVCModelo(1);
					System.out.println("--------- \\nDar latitud a buscar:");
					val1 = lector.nextDouble();
					System.out.println("--------- \\nDar longitud a buscar:");
					val2 = lector.nextDouble();
					Queue<String> cola2 = modelo.BuscarNodosMallaVial(val1, val2);
					Node actual2 = cola2.darPrimerNodo();
					while(actual2 != null)
					{
						String cadena = (String) actual2.darItem();
						System.out.println(cadena);
						actual2 = actual2.darSiguiente();
					}
					
				case 7:
					modelo = new MVCModelo(1);
					System.out.println("--------- \\nDar límite inferior del tiempo de espera a buscar a buscar:");
					val1 = lector.nextDouble();
					System.out.println("--------- \\nDar límite superior del tiempo de espera a buscar a buscar:");
					val2 = lector.nextDouble();
					System.out.println("--------- \\nDar cantidad N a buscar:");
					val3 = lector.nextInt();
					Queue<String> cola3 = modelo.BuscarEnUnRangoYPrimerTrimestreDesviacion2018(val1, val2, val3);
					Node actual3 = cola3.darPrimerNodo();
					while(actual3 != null)
					{
						String cadena = (String) actual3.darItem();
						System.out.println(cadena);
						actual3 = actual3.darSiguiente();
					}
				case 8:
					System.out.println("--------- \nCrear MVCModelo \nDar trimestre que desea cargar: ");
				    trim = lector.nextInt();
				    modelo = new MVCModelo(trim); 
				    System.out.println("--------- \\nDar zona de salida");
				    val3 = lector.nextInt();
				    System.out.println("--------- \\nDar hora de salida");
				    val4 = lector.nextInt();
				    Queue<String> cola4 = modelo.ViajesConHoraYZonaOrigenDada(val3, val4);
					Node actual4 = cola4.darPrimerNodo();
					while(actual4 != null)
					{
						String cadena = (String) actual4.darItem();
						System.out.println(cadena);
						actual4 = actual4.darSiguiente();
					}
				case 9:
					System.out.println("--------- \nCrear MVCModelo \nDar trimestre que desea cargar: ");
				    trim = lector.nextInt();
				    modelo = new MVCModelo(trim);
				    System.out.println("--------- \\nDar hora inicial");
				    val3 = lector.nextInt();
				    System.out.println("--------- \\nDar hora final");
				    val4 = lector.nextInt();
				    System.out.println("--------- \\nDar zona de llegada");
				    val5 = lector.nextInt();
				    Queue<String> cola5 = modelo.ViajesEnRangoDeHorasYZonaDestinoDada(val5, val3, val4);
					Node actual5 = cola5.darPrimerNodo();
					while(actual5 != null)
					{
						String cadena = (String) actual5.darItem();
						System.out.println(cadena);
						actual5 = actual5.darSiguiente();
					}
				case 10:
					modelo = new MVCModelo(1);
					System.out.println("--------- \\nDar cantidad N de zonas a buscar");
					val3 = lector.nextInt();
				    modelo.darNZonasPorNodos(val3);
				case 11:
					modelo = new MVCModelo(1);
					modelo.GraficaASCII();
				case 12:
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
