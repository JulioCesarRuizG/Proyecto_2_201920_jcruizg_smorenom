package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;

import model.data_structures.ArbolRN;
import model.data_structures.Feature;
import model.data_structures.FeatureCollection;
import model.data_structures.HashLP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.Viaje;

/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private class A1Letras implements Comparable<A1Letras>{
		/* Se usará para el punto 1A. Cuando se lea una linea, se usará el método agregar nombre para agregar el nombre de la zona 
		 * Si todaviá no hay una zona con la misma primera letra en el nombre, se creará un nuevo A1Letras y se agregará al heap con ese nombre
		 */
		private char letra;
		private int cantidad;
		Queue<String> nombres;
		public A1Letras(char pLetra, String pNombre){
			letra=pLetra;
			nombres= new Queue<String>(new Node<String>(pNombre,null));
			cantidad= nombres.size();
		}
		public void agregarNombre(String pNombre){
			nombres.enQueue(pNombre);
			cantidad++;
		}
		public int compareTo(A1Letras o) {
			if(cantidad==o.cantidad)
				return 0;
			if(cantidad<o.cantidad)
				return-1;
			else
				return 1;
		}

	}
	private class ValorViajesRango implements Comparable<ValorViajesRango>{
		/*
		 * Se utilizará para guardar los datos necesarios en los puntos 3A y 3B
		 */
		private int zonaOrigen;
		private int zonaDestino;
		private int mes;
		public ValorViajesRango(int pOrigen, int pDestino, int pMes){
			zonaOrigen=pOrigen;
			zonaDestino=pDestino;
			mes=pMes;

		}
		@Override
		public int compareTo(ValorViajesRango o) {

			if(zonaOrigen==o.zonaOrigen){
				if(zonaDestino==o.zonaDestino)
					return 0;
				if(zonaDestino<o.zonaDestino)
					return -1;
				else
					return 1;
			}
			if(zonaOrigen<o.zonaOrigen)
				return-1;
			else
				return 1;

		}}
	private class ValorMasAlNorte implements Comparable<ValorMasAlNorte> {
		/*
		 * Se utilizará para guardar los datos necesarios en el punto 1B
		 */
		private double latitud;
		private double longitud;
		private String nombre;
		public ValorMasAlNorte(double pLongitud, String pNombre, double pLatitud){
			longitud=pLongitud;
			nombre=pNombre;
			latitud=pLatitud;

		}
		@Override
		public int compareTo(ValorMasAlNorte o) {
			if(latitud==o.latitud)
				return 0;
			if(latitud<o.latitud)
				return -1;
			else return 1;
		}


	}


	private class ZonaYHoraDadaKey implements Comparable<ZonaYHoraDadaKey>  {
		/*
		 * Se utilizará para guardar las keys en el punto 1C
		 */
		private int zona;
		private int hora;
		public ZonaYHoraDadaKey(int pHora, int pZona){
			hora=pHora;
			zona=pZona;

		}
		@Override
		public int compareTo(ZonaYHoraDadaKey o) {
			if(zona==o.zona&&hora==o.hora)
				return 0;
			if((zona+""+hora).compareTo(o.zona+""+o.hora)==-1){
				return -1;
			}
			else return 1;
		}

	}
	private class ZonaYHoraDadaValue  {
		/*
		 * Se utilizará para guardar los values en el punto 1C
		 */
		private int zona;
		private double tiempoPromedio;
		public ZonaYHoraDadaValue(double pTPromedio, int pZona){
			tiempoPromedio=pTPromedio;
			zona=pZona;

		}



	}
	private class NNodosYNombre3C implements Comparable<NNodosYNombre3C>  {
		/*
		 * Se utilizará para guardar las keys en el punto 1C
		 */
		private String nombre;
		private int nNodos;
		public NNodosYNombre3C(int pNNodos, String pNombre){
			nNodos=pNNodos;
			nombre=pNombre;

		}
		@Override
		public int compareTo(NNodosYNombre3C o) {
			if(nNodos==o.nNodos)
				return 0;
			if(nNodos<o.nNodos){
				return -1;
			}
			else return 1;
		}

	}

	private class NodosDelimitanZonaLocalizacionKey implements Comparable<NodosDelimitanZonaLocalizacionKey> {
		/*
		 * Se utilizará para guardar los datos necesarios en el punto 1B
		 */
		private double latitud;
		private double longitud;
		private int decimales;
		public NodosDelimitanZonaLocalizacionKey(double pLongitud, double pLatitud,int pDecimales){
			longitud=pLongitud;
			latitud=pLatitud;
			decimales=pDecimales;

		}
		@Override
		public int compareTo(NodosDelimitanZonaLocalizacionKey o) {
			double latitudTruncada = ((int)latitud*10^decimales)/(10^decimales);
			int longitudTruncada =((int)longitud*10^decimales)/(10^decimales);
			double olatitudTruncada = ((int)o.latitud*10^decimales)/(10^decimales);
			int olongitudTruncada =((int)o.longitud*10^decimales)/(10^decimales);


			if(latitudTruncada==olatitudTruncada){
				if(longitudTruncada==olongitudTruncada)
					return 0;
				if(longitudTruncada>olongitudTruncada)
					return 1;
				else return -1;
			}
			if(latitudTruncada<olatitudTruncada)
				return -1;
			else return 1;
		}
	}
	private class NodosDelimitanZonaLocalizacionValue2A  {
		/*
		 * Se utilizará para guardar los datos necesarios en el punto 2A
		 */
		private double latitud;
		private double longitud;
		private String nombre;
		public NodosDelimitanZonaLocalizacionValue2A(double pLongitud, double pLatitud,String pNombre){
			longitud=pLongitud;
			latitud=pLatitud;
			nombre=pNombre;

		}}
	private class NodosDelimitanZonaLocalizacionValue2B  {
		/*
		 * Se utilizará para guardar los datos necesarios en el punto 2B
		 */
		private double latitud;
		private double longitud;
		private int ID;
		public NodosDelimitanZonaLocalizacionValue2B(double pLongitud, double pLatitud,int pID){
			longitud=pLongitud;
			latitud=pLatitud;
			ID=pID;

		}
	}

	private MaxHeapCP<A1Letras> heap1A;
	/*
	 * Este arbol se utilizará para el punto 3A del proyecto. Tendrá como key el tiempo promedio de los viajes que lea
	 * En el caso de que haya dos viajes con el mismo nombre, se agregará de igual manera al queue de ValorViajesRango.
	 * Se usará el método getKeysInRange y getValuesInRange  para esto y se organizará en base a las zonas de origen y destino posteriormente
	 */
	private ArbolRN<Double,ValorViajesRango>  Arbol3A;
	//Igual que 3A, pero con la desviación estandar en lugar de el tiempo promedio
	private ArbolRN<Double,ValorViajesRango>  Arbol3B;
	//Como 1A, pero  para 1B y toca revisar cual es la latitud mayor
	private MaxHeapCP<ValorMasAlNorte> heap1B;

	private HashLP<NodosDelimitanZonaLocalizacionKey,NodosDelimitanZonaLocalizacionValue2A> hash2A;
	private HashLP<NodosDelimitanZonaLocalizacionKey,NodosDelimitanZonaLocalizacionValue2B> hash2B;
	/*
	 * Se Utilizará para para el punto 1C, y se designará la zona de origen y la hora como key, usando una clase privada
	 */
	private HashLP<ZonaYHoraDadaKey,ZonaYHoraDadaValue> hash1C;
	/*
	 * Se Utilizará para para el punto 2C, y se designará la zona de Destino y la hora como key, usando una clase privada
	 */
	private ArbolRN<ZonaYHoraDadaKey,ZonaYHoraDadaValue> Arbol2C;
	/*
	 * Se Utilizará para para el punto 3C, y el número de nodos se utilizará para la función compareTo 
	 */
	private MaxHeapCP<NNodosYNombre3C> heap3C;

	private int cargados;

	private boolean valor=true;
	private Viaje agregar;
	private FeatureCollection valoresJson;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	// por alguna razón, no carga correctamente el excel que se nos entrega. Pero, si utilizamos uno con el mismo orden y tipo de datos pero que solo tiene mil lineas,funciona correctamente
	public MVCModelo(int n) throws Exception{

		String path = "./data/test2.json";
		JsonReader reader;
		Gson gson = new Gson();
		try {
			reader = new JsonReader(new FileReader(path));
			FeatureCollection lista3 = gson.fromJson(reader, FeatureCollection.class);
			valoresJson = lista3;
			Feature feature =lista3.getFeatures()[1];
			String valor = feature.darPropiedades().darScanombre();
			if( valor.equals("LA MAGDALENA"))
			{
				System.out.println("Se ha cargado bien 1 de 5");
			}
			else
			{
				System.out.println("Error al cargar 1 de 5");
				System.out.println(feature.darPropiedades().darScanombre());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		heap1A = new MaxHeapCP<A1Letras>();
		
		A1Letras[] agregar2 = null;
		for(Feature features : valoresJson.getFeatures())
		{
			int valor = 0;
			String nombre = features.darPropiedades().darScanombre();
			char letraChar = nombre.toLowerCase().charAt(0);
			String letra = nombre.charAt(0) + "";
			if(letra.equalsIgnoreCase("a"))
			{
				valor = 0;
			}
			else if(letra.equalsIgnoreCase("b"))
			{
				valor = 1;
			}
			else if(letra.equalsIgnoreCase("c"))
			{
				valor = 2;
			}
			else if(letra.equalsIgnoreCase("d"))
			{
				valor = 3;
			}
			else if(letra.equalsIgnoreCase("e"))
			{
				valor = 4;
			}
			else if(letra.equalsIgnoreCase("f"))
			{
				valor = 5;
			}
			else if(letra.equalsIgnoreCase("g"))
			{
				valor = 6;
			}
			else if(letra.equalsIgnoreCase("h"))
			{
				valor = 7;
			}
			else if(letra.equalsIgnoreCase("i"))
			{
				valor = 8;
			}
			else if(letra.equalsIgnoreCase("j"))
			{
				valor = 9;
			}
			else if(letra.equalsIgnoreCase("k"))
			{
				valor = 10;
			}
			else if(letra.equalsIgnoreCase("l"))
			{
				valor = 11;
			}
			else if(letra.equalsIgnoreCase("m"))
			{
				valor = 12;
			}
			else if(letra.equalsIgnoreCase("n"))
			{
				valor = 13;
			}
			else if(letra.equalsIgnoreCase("ñ"))
			{
				valor = 14;
			}
			else if(letra.equalsIgnoreCase("o"))
			{
				valor = 15;
			}
			else if(letra.equalsIgnoreCase("p"))
			{
				valor = 16;
			}
			else if(letra.equalsIgnoreCase("q"))
			{
				valor = 17;
			}
			else if(letra.equalsIgnoreCase("r"))
			{
				valor = 18;
			}
			else if(letra.equalsIgnoreCase("s"))
			{
				valor = 19;
			}
			else if(letra.equalsIgnoreCase("t"))
			{
				valor = 20;
			}
			else if(letra.equalsIgnoreCase("u"))
			{
				valor = 21;
			}
			else if(letra.equalsIgnoreCase("v"))
			{
				valor = 22;
			}
			else if(letra.equalsIgnoreCase("w"))
			{
				valor = 23;
			}
			else if(letra.equalsIgnoreCase("x"))
			{
				valor = 24;
			}
			else if(letra.equalsIgnoreCase("y"))
			{
				valor = 25;
			}
			else
			{
				valor = 26;
			}
			
			if(agregar2[valor] != null)
			{
				agregar2[valor].agregarNombre(nombre);
			}
			else
			{
				agregar2[valor] = new A1Letras(letraChar, nombre);
			}
			
		}
		for(A1Letras todas : agregar2)
		{
			heap1A.agregar(todas);
		}

		String pRutaM="";
		String pRutaS="";
		String pRutaH="";
		boolean primerTrimestre=false;
		if(n == 1)
		{
			pRutaM="./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv";
			pRutaH="./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
			pRutaS="./data/bogota-cadastral-2018-1-WeeklyAggregate.csv";
			primerTrimestre=true;
		}
		else if(n == 2)
		{
			pRutaM="./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv";
			pRutaH="./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv";
			pRutaS="./data/bogota-cadastral-2018-2-WeeklyAggregate.csv";
		}
		else if(n == 3)
		{
			pRutaM="./data/bogota-cadastral-2018-3-All-MonthlyAggregate.csv";
			pRutaH="./data/bogota-cadastral-2018-3-All-HourlyAggregate.csv";
			pRutaS="./data/bogota-cadastral-2018-3-WeeklyAggregate.csv";
		}
		else
		{
			pRutaM="./data/bogota-cadastral-2018-4-All-MonthlyAggregate.csv";
			pRutaH="./data/bogota-cadastral-2018-4-All-HourlyAggregate.csv";
			pRutaS="./data/bogota-cadastral-2018-4-WeeklyAggregate.csv";
		}


		String txtruta = "./data/Nodes_of_red_vial-wgs84_shp.txt";

		CSVReader reader1 = null;
		CSVReader reader2 = null;
		CSVReader reader3 = null;
		cargados = 0;
		Arbol3A = new ArbolRN<Double, MVCModelo.ValorViajesRango>(); //Asignado
		Arbol3B = new ArbolRN<Double, MVCModelo.ValorViajesRango>(); //Asignado
		hash1C = new HashLP<MVCModelo.ZonaYHoraDadaKey, MVCModelo.ZonaYHoraDadaValue>(11); //Asignado
		Arbol2C = new ArbolRN<MVCModelo.ZonaYHoraDadaKey, MVCModelo.ZonaYHoraDadaValue>(); //Asignado


		try {
			reader1= new CSVReader(new FileReader(pRutaM));
			for(String[] nextLine : reader1) {
				if(nextLine[0].toString().contains("sourceid"))
				{

				}
				else
				{
					int inicioID = Integer.parseInt(nextLine[0]);
					int destinoID=Integer.parseInt(nextLine[1]);
					int Mes=Integer.parseInt(nextLine[2]);
					double tiempoPromedioEnSegundos=Double.parseDouble(nextLine[3]);
					double desviacionEstandar=Double.parseDouble(nextLine[4]);
					double tiempoPromedioGEnSegundos=Double.parseDouble(nextLine[5]);
					double desviacionEstandarG=Double.parseDouble(nextLine[6]);
					if(primerTrimestre){
						Arbol3A.put(tiempoPromedioEnSegundos, new ValorViajesRango(inicioID,destinoID,Mes));
						Arbol3B.put(desviacionEstandar, new ValorViajesRango(inicioID,destinoID,Mes));
					}

					Viaje i = new Viaje(inicioID,destinoID,Mes,tiempoPromedioEnSegundos,desviacionEstandar,tiempoPromedioGEnSegundos,desviacionEstandarG);
					agregar = i;

					cargados++;
				}
			}
			if(cargados == 1549588 || cargados == 1599017 || cargados == 1569753 || cargados == 1549081)
			{
				System.out.println("Se ha cargado bien 2 de 5");
			}
			else
			{
				System.out.println("Error al cargar 2 de 5");
				System.out.println(cargados);
			}
			cargados = 0;

			reader2= new CSVReader(new FileReader(pRutaH));
			for(String[] nextLine : reader2) {
				if(nextLine[0].toString().contains("sourceid"))
				{

				}
				else
				{


					int  inicioID=Integer.parseInt(nextLine[0]);
					int destinoID=Integer.parseInt(nextLine[1]);
					int hora=Integer.parseInt(nextLine[2]);
					double tiempoPromedioEnSegundos=Double.parseDouble(nextLine[3]);
					double desviacionEstandar=Double.parseDouble(nextLine[4]);
					double tiempoPromedioGEnSegundos=Double.parseDouble(nextLine[5]);
					double desviacionEstandarG=Double.parseDouble(nextLine[6]);
					hash1C.put(new ZonaYHoraDadaKey(hora,inicioID), new ZonaYHoraDadaValue(tiempoPromedioEnSegundos,destinoID));
					Arbol2C.put(new ZonaYHoraDadaKey(hora,destinoID), new ZonaYHoraDadaValue(tiempoPromedioEnSegundos,inicioID));
					Viaje i = new Viaje(inicioID,destinoID,hora,tiempoPromedioEnSegundos,desviacionEstandar,tiempoPromedioGEnSegundos,desviacionEstandarG);
					agregar = i;

					cargados++;

				}
			}

			if(cargados == 10067605 || cargados == 10479572 || cargados == 10259375 || cargados == 10121815)
			{
				System.out.println("Se ha cargado bien 3 de 5");
			}
			else
			{
				System.out.println("Error al cargar 3 de 5");
				System.out.println(cargados);
			}
			cargados = 0;

			reader3= new CSVReader(new FileReader(pRutaS));
			for(String[] nextLine : reader3) {
				if(nextLine[0].toString().contains("sourceid"))
				{

				}
				else
				{


					int  inicioID=Integer.parseInt(nextLine[0]);
					int destinoID=Integer.parseInt(nextLine[1]);
					int hora=Integer.parseInt(nextLine[2]);
					double tiempoPromedioEnSegundos=Double.parseDouble(nextLine[3]);
					double desviacionEstandar=Double.parseDouble(nextLine[4]);
					double tiempoPromedioGEnSegundos=Double.parseDouble(nextLine[5]);
					double desviacionEstandarG=Double.parseDouble(nextLine[6]);

					Viaje i = new Viaje(inicioID,destinoID,hora,tiempoPromedioEnSegundos,desviacionEstandar,tiempoPromedioGEnSegundos,desviacionEstandarG);
					agregar = i;

					cargados++;
				}
			}
			if(cargados == 3548973 || cargados == 3631367 || cargados == 3556446 || cargados == 3549504)
			{
				System.out.println("Se ha cargado bien 4 de 5");
			}
			else
			{
				System.out.println("Error al cargar 4 de 5");
				System.out.println(cargados);
			}
			cargados = 0;

			FileReader lector = new FileReader(txtruta);
			BufferedReader leer = new BufferedReader(lector);
			String lineaActual = leer.readLine();
			while(lineaActual != "" && lineaActual != null)
			{
				//				String[] valores = lineaActual.split(",");
				//				valores[0]
				//				valores[1]//Falta asignar
				//				valores[2]		
				lineaActual = leer.readLine();
				cargados++;
			}
			if(cargados == 228046)
			{
				System.out.println("Se ha cargado bien 5 de 5");
			}
			else
			{
				System.out.println("Error al cargar 5 de 5");
				System.out.println(cargados);
			}
			cargados = 0;

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if (reader1 != null) {
				try {
					reader1.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader2 != null) {
				try {
					reader2.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader3 != null) {
				try {
					reader3.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Parte A
	 */
	
	//1A
	public void darNLetrasMásFrecuentes(int n) throws Exception
	{
		int iter = n;
		while(iter != 0)
		{
			A1Letras sacada = heap1A.sacarMax();
			System.out.println("La letra fue: " + sacada.letra);
			Node actual = sacada.nombres.darPrimerNodo();
			String nombres = "";
			while(actual != null)
			{
				String dato = (String) actual.darItem();
				nombres = nombres + dato + ", ";
				actual = actual.darSiguiente();
			}
			System.out.println(nombres.substring(0, nombres.length()-2));
			n--;
		}
	}
	
	/*
	 * Parte B
	 */
	
	//2B
	public Queue<String> BuscarNodosMallaVial(double pLatitud, double pLongitud){
        Queue<String> NodosRespuesta = new Queue<String>(null);
        Queue<NodosDelimitanZonaLocalizacionValue2B> NodosHash= hash2B.get(new NodosDelimitanZonaLocalizacionKey(pLatitud,pLongitud,2)); 
        while(NodosHash.size()!=0){
            NodosDelimitanZonaLocalizacionValue2B dato= NodosHash.deQueue();
            NodosRespuesta.enQueue("ID="+dato.ID+", Latitud="+ dato.latitud+", Longitud=" +dato.longitud);
        }
        return NodosRespuesta;
    }
}
