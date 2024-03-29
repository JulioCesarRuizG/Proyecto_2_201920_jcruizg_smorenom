package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

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
import model.logic.MVCModelo.ValorViajesRango;
import model.logic.MVCModelo.ZonaYHoraDadaKeyConTrim;
import model.logic.MVCModelo.ZonaYHoraDadaValue;

/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private class A1Letras implements Comparable<A1Letras>{
		/* Se usar� para el punto 1A. Cuando se lea una linea, se usar� el m�todo agregar nombre para agregar el nombre de la zona 
		 * Si todavi� no hay una zona con la misma primera letra en el nombre, se crear� un nuevo A1Letras y se agregar� al heap con ese nombre
		 */
		private char letra;
		private int cantidad;
		Queue<String> nombres;
		public A1Letras(char pLetra, String pNombre){
			letra=pLetra;
			nombres= new Queue<String>(new Node<String>(pNombre,null));
			cantidad= 1;
		}
		public void agregarNombre(String pNombre){
			nombres.enQueue(pNombre);
			cantidad++;
		}
		public int compareTo(A1Letras o) {
			if(cantidad>o.cantidad)
				return 1;
			else if(cantidad<o.cantidad)
				return-1;
			return 0;
		}

	}
	public class ValorViajesRango implements Comparable<ValorViajesRango>{
		/*
		 * Se utilizar� para guardar los datos necesarios en los puntos 3A y 3B
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
	private class ValorViajesRangoConKey implements Comparable<ValorViajesRangoConKey>{
		/*
		 * Se utilizar� para guardar los datos necesarios en los puntos 3A y 3B
		 */
		private int zonaOrigen;
		private int zonaDestino;
		private int mes;
		private double key;
		public ValorViajesRangoConKey(int pOrigen, int pDestino, int pMes, double pKey){
			zonaOrigen=pOrigen;
			zonaDestino=pDestino;
			mes=pMes;
			key=pKey;

		}
		@Override
		public int compareTo(ValorViajesRangoConKey o) {

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
		 * Se utilizar� para guardar los datos necesarios en el punto 1B
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
		 * Se utilizar� para guardar las keys en el punto 1C
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

	public class ZonaYHoraDadaKeyConTrim implements Comparable<ZonaYHoraDadaKeyConTrim>  {
		/*
		 * Se utilizar� para guardar las keys en el punto 1C
		 */
		private int zona;
		private int hora;
		private int trim;
		public ZonaYHoraDadaKeyConTrim(int pHora, int pZona, int ptrim){
			hora=pHora;
			zona=pZona;
			trim = ptrim;
		}
		@Override
		public int compareTo(ZonaYHoraDadaKeyConTrim o) {
			if(zona==o.zona&&hora==o.hora&&trim==o.trim)
				return 0;
			if((zona+""+hora+""+trim).compareTo(o.zona+""+o.hora+""+o.trim)==-1){
				return -1;
			}
			else return 1;
		}

	}
	public class ZonaYHoraDadaValue  {
		/*
		 * Se utilizar� para guardar los values en el punto 1C
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
		 * Se utilizar� para guardar las keys en el punto 1C
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
		 * Se utilizar� para guardar los datos necesarios en el punto 1B
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
		 * Se utilizar� para guardar los datos necesarios en el punto 2A
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
		 * Se utilizar� para guardar los datos necesarios en el punto 2B
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

	private class NodosLimitan3C implements Comparable<NodosLimitan3C>
	{
		private double tama�o;
		public NodosLimitan3C(int pTam)
		{
			tama�o = pTam;
		}
		@Override
		public int compareTo(NodosLimitan3C o) {
			if(tama�o > o.tama�o)
			{
				return 1;
			}
			else if(tama�o < o.tama�o)
			{
				return -1;
			}
			return 0;
		}
	}

	private MaxHeapCP<A1Letras> heap1A;
	/*
	 * Este arbol se utilizar� para el punto 3A del proyecto. Tendr� como key el tiempo promedio de los viajes que lea
	 * En el caso de que haya dos viajes con el mismo nombre, se agregar� de igual manera al queue de ValorViajesRango.
	 * Se usar� el m�todo getKeysInRange y getValuesInRange  para esto y se organizar� en base a las zonas de origen y destino posteriormente
	 */
	private ArbolRN<Double,ValorViajesRango>  Arbol3A;
	//Igual que 3A, pero con la desviaci�n estandar en lugar de el tiempo promedio
	private ArbolRN<Double,ValorViajesRango>  Arbol3B;
	//Como 1A, pero  para 1B y toca revisar cual es la latitud mayor
	private MaxHeapCP<ValorMasAlNorte> heap1B;

	private HashLP<NodosDelimitanZonaLocalizacionKey,NodosDelimitanZonaLocalizacionValue2A> hash2A;
	private HashLP<NodosDelimitanZonaLocalizacionKey,NodosDelimitanZonaLocalizacionValue2B> hash2B;
	private HashLP<ZonaYHoraDadaKeyConTrim,ZonaYHoraDadaValue> hash4C;
	/*
	 * Se Utilizar� para para el punto 1C, y se designar� la zona de origen y la hora como key, usando una clase privada
	 */
	private HashLP<ZonaYHoraDadaKey,ZonaYHoraDadaValue> hash1C;
	/*
	 * Se Utilizar� para para el punto 2C, y se designar� la zona de Destino y la hora como key, usando una clase privada
	 */
	private ArbolRN<ZonaYHoraDadaKey,ZonaYHoraDadaValue> Arbol2C;
	/*
	 * Se Utilizar� para para el punto 3C, y el n�mero de nodos se utilizar� para la funci�n compareTo 
	 */
	private MaxHeapCP<NNodosYNombre3C> heap3C;

	private int cargados;

	private boolean valor=true;
	private Viaje agregar;
	private FeatureCollection valoresJson;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	// por alguna raz�n, no carga correctamente el excel que se nos entrega. Pero, si utilizamos uno con el mismo orden y tipo de datos pero que solo tiene mil lineas,funciona correctamente
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

		hash1C = new HashLP<MVCModelo.ZonaYHoraDadaKey, MVCModelo.ZonaYHoraDadaValue>(13);
		heap1A = new MaxHeapCP<A1Letras>();
		heap3C = new MaxHeapCP<NNodosYNombre3C>();
		heap1B = new MaxHeapCP<ValorMasAlNorte>();
		hash2A = new HashLP<NodosDelimitanZonaLocalizacionKey, NodosDelimitanZonaLocalizacionValue2A>(7);

		A1Letras[] agregar2 = new A1Letras[27];
		for(Feature features : valoresJson.getFeatures())
		{
			{
				int num = 0;
				for(double[][][] niv1 : features.darMultiPolygon().darCoordenadas())
				{
					double[][] niv2 = niv1[0];
					for(double[] niv3 : niv2)
					{
						num++;
						ValorMasAlNorte val = new ValorMasAlNorte(niv3[0], features.darPropiedades().darScanombre(), niv3[1]);
						heap1B.agregar(val);
						NodosDelimitanZonaLocalizacionKey llave = new NodosDelimitanZonaLocalizacionKey(niv3[1], niv3[0], 3);
						NodosDelimitanZonaLocalizacionValue2A valor = new NodosDelimitanZonaLocalizacionValue2A(niv3[1], niv3[0], features.darPropiedades().darScanombre());
						hash2A.put(llave, valor);
					}
				}
				NNodosYNombre3C nodos = new NNodosYNombre3C(num, features.darPropiedades().darScanombre());
				heap3C.agregar(nodos);
			}
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
			else if(letra.equalsIgnoreCase("�"))
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
			if(todas == null)
			{

			}
			else
			{
				heap1A.agregar(todas);
			}
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
		hash1C = new HashLP<MVCModelo.ZonaYHoraDadaKey, MVCModelo.ZonaYHoraDadaValue>(13); //Asignado
		Arbol2C = new ArbolRN<MVCModelo.ZonaYHoraDadaKey, MVCModelo.ZonaYHoraDadaValue>(); //Asignado
		hash2B = new HashLP<NodosDelimitanZonaLocalizacionKey,NodosDelimitanZonaLocalizacionValue2B>(13);
		hash4C = new HashLP<ZonaYHoraDadaKeyConTrim, ZonaYHoraDadaValue>(13);


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
			boolean primer = false;
			if(pRutaH.equals("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"))
			{
				primer = true;
			}

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
								if(primer == true)
								{
									hash4C.put(new ZonaYHoraDadaKeyConTrim(hora,inicioID,1), new ZonaYHoraDadaValue(tiempoPromedioEnSegundos,destinoID));
								}
								agregar = i;
			
								cargados++;
			
							}
						}
			if(primer == true)
			{
				reader3= new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));
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
						hash4C.put(new ZonaYHoraDadaKeyConTrim(hora,inicioID,2), new ZonaYHoraDadaValue(tiempoPromedioEnSegundos,destinoID));
						Viaje i = new Viaje(inicioID,destinoID,hora,tiempoPromedioEnSegundos,desviacionEstandar,tiempoPromedioGEnSegundos,desviacionEstandarG);
						agregar = i;

					}
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
				String[] valores = lineaActual.split(",");
				int ID= Integer.parseInt(valores[0]);
				double longitud= Double.parseDouble(valores[1]);
				double latitud= Double.parseDouble(valores[2]);
				hash2B.put(new NodosDelimitanZonaLocalizacionKey(longitud, latitud, 2),new NodosDelimitanZonaLocalizacionValue2B(longitud, latitud,ID));	
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
	public void darNLetrasM�sFrecuentes(int n) throws Exception
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
			iter--;
		}
	}

	//2A
	public Queue<String> BuscarNodosDelimitacionGeografica(double pLatitud, double pLongitud){
		Queue<String> NodosRespuesta = new Queue<String>(null);
		Queue<NodosDelimitanZonaLocalizacionValue2A> NodosHash= hash2A.get(new NodosDelimitanZonaLocalizacionKey(pLatitud,pLongitud,3)); 
		while(NodosHash.size()!=0){
			NodosDelimitanZonaLocalizacionValue2A dato= NodosHash.deQueue();
			NodosRespuesta.enQueue("Nombre="+dato.nombre+", Latitud="+ dato.latitud+", Longitud=" +dato.longitud);
		}
		return NodosRespuesta;
	}

	//3A
	public Queue<String> BuscarEnUnRangoYPrimerTrimestreTiempoPromedio2018(double limiteInicial, double limiteFinal, int N) throws Exception{
		int contador=0;
		if(Arbol3A.size()!=0){
			Queue<String> viajes= new Queue<String>(null);
			Iterable<Double> keys=Arbol3A.keys();
			Iterable<Queue<ValorViajesRango>> values=Arbol3A.valuesInRange(limiteInicial, limiteFinal);
			Iterator<Queue<ValorViajesRango>> iteradorValues=values.iterator();
			Iterator<Double> iteradorKeys=keys.iterator();
			MaxHeapCP<ValorViajesRangoConKey> organizador= new MaxHeapCP<ValorViajesRangoConKey>();
			while(iteradorKeys.hasNext()&&contador<N){
				Double TPromedio=iteradorKeys.next();
				Queue<ValorViajesRango> ColaValor= iteradorValues.next();
				Iterator<ValorViajesRango> iteradorColaValor=  ColaValor.iterator();
				while(iteradorColaValor.hasNext()&&contador<N){
					ValorViajesRango Valor = iteradorColaValor.next();
					organizador.agregar(new ValorViajesRangoConKey(Valor.zonaOrigen,Valor.zonaDestino, Valor.mes, TPromedio));
					contador++;
				}
			}
			while(!organizador.esVacia()){
				ValorViajesRangoConKey Valor= organizador.sacarMax();
				viajes.enQueue("Tiempo Promedio:"+ Valor.key +", Zona Origen:" +Valor.zonaOrigen+ ", Zona Destino:"+ Valor.zonaDestino+ ", Mes:" + Valor.mes);
			}
			return viajes;
		}else{
			return new Queue<String>(null);
		}
	}
	/*
	 * Parte B
	 */

	//1B
	public void darNZonasM�sAlNorte(int n) throws Exception
	{
		int can = n;
		while(can != 0)
		{
			ValorMasAlNorte nodo =heap1B.sacarMax();
			System.out.println("El punto de la zona: " + nodo.nombre + " m�s al norte est� en las coordenadas: " + nodo.latitud + ", " + nodo.longitud);
			can--;
		}
	}


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
	//3B
	public Queue<String> BuscarEnUnRangoYPrimerTrimestreDesviacion2018(double limiteInicial, double limiteFinal, int N) throws Exception{
		int contador = 0;
		if(Arbol3B.size()!=0){
			Queue<String> viajes= new Queue<String>(null);
			Iterable<Double> keys=Arbol3B.keys();
			Iterable<Queue<ValorViajesRango>> values=Arbol3B.valuesInRange(limiteInicial, limiteFinal);
			Iterator<Queue<ValorViajesRango>> iteradorValues=values.iterator();
			Iterator<Double> iteradorKeys=keys.iterator();
			MaxHeapCP<ValorViajesRangoConKey> organizador= new MaxHeapCP<ValorViajesRangoConKey>();
			while(iteradorKeys.hasNext()&&contador<N){
				Double DesviacionEstandar=iteradorKeys.next();
				Queue<ValorViajesRango> ColaValor= iteradorValues.next();
				Iterator<ValorViajesRango> iteradorColaValor=  ColaValor.iterator();
				while(iteradorColaValor.hasNext()&&contador<N){
					ValorViajesRango Valor = iteradorColaValor.next();
					organizador.agregar(new ValorViajesRangoConKey(Valor.zonaOrigen,Valor.zonaDestino, Valor.mes, DesviacionEstandar));
					contador++;
				}
			}
			while(!organizador.esVacia()){
				ValorViajesRangoConKey Valor= organizador.sacarMax();
				viajes.enQueue("Desviacion Estandar:"+ Valor.key +", Zona Origen:" +Valor.zonaOrigen+ ", Zona Destino:"+ Valor.zonaDestino+ ", Mes:" + Valor.mes);
			}
			return viajes;
		}else{
			return new Queue<String>(null);
		}
	}
	//Parte C

	//1C
	public Queue<String> ViajesConHoraYZonaOrigenDada(int pZonaSalida, int pHora){
		Queue<String> viajes = new Queue<String>(null);
		ZonaYHoraDadaKey keyBuscada = new ZonaYHoraDadaKey(pHora,pZonaSalida);
		Queue<ZonaYHoraDadaValue> valores=hash1C.get(keyBuscada);
		if(valores.isEmpty())
		{
			System.out.println("No hay viajes para mostrar");
		}
		else {
			while(!valores.isEmpty()){
				ZonaYHoraDadaValue valor= valores.deQueue();
				viajes.enQueue("Zona Salida:"+ pZonaSalida+", Zona Destino:"+valor.zona+", Hora:"+ pHora+ ", Tiempo Promedio:"+ valor.tiempoPromedio);
			}
		}

		return viajes;
	}
	//2C
	public Queue<String> ViajesEnRangoDeHorasYZonaDestinoDada(int pZonaLlegada, int pHoraInicial, int pHoraFinal){
		Queue<String> viajes = new Queue<String>(null);
		ZonaYHoraDadaKey keyInicial = new ZonaYHoraDadaKey(pHoraInicial,pZonaLlegada);
		ZonaYHoraDadaKey keyFinal = new ZonaYHoraDadaKey(pHoraFinal,pZonaLlegada);
		Iterable<ZonaYHoraDadaKey> keys= Arbol2C.keys(keyInicial,keyFinal);
		Iterable<Queue<ZonaYHoraDadaValue>> values= Arbol2C.valuesInRange(keyInicial, keyFinal);
		Iterator<ZonaYHoraDadaKey> iteradorKeys= keys.iterator();
		Iterator<Queue<ZonaYHoraDadaValue>> iteradorValues= values.iterator();
		while(iteradorKeys.hasNext()){
			ZonaYHoraDadaKey llave=iteradorKeys.next();
			int hora = llave.hora;
			Queue<ZonaYHoraDadaValue> colaValue= iteradorValues.next();
			Iterator<ZonaYHoraDadaValue> iteradorColaValue= colaValue.iterator();
			while(iteradorColaValue.hasNext()){
				ZonaYHoraDadaValue Valor = iteradorColaValue.next();
				viajes.enQueue("Zona Origen:"+ Valor.zona+", Zona Destino:"+pZonaLlegada +", Hora:"+hora +", Tiempo Promedio:"+Valor.tiempoPromedio);
			}
		}


		return viajes;
	}

	//3C
	public void darNZonasPorNodos(int n) throws Exception
	{
		int can = n;
		while(can != 0)
		{
			NNodosYNombre3C nodo =heap3C.sacarMax();
			System.out.println("La zona: " + nodo.nombre + " tiene un total de " + nodo.nNodos + " nodos en su frontera");
			can--;
		}
	}


	//4C	
	public void GraficaASCII()
	{
		int[] faltantes = null;
		for(int Ftrim = 1; Ftrim<3 ; Ftrim++)
		{
			for(int Fhora = 0 ; Fhora < 24 ; Fhora++)
			{
				for(int Fzona = 0 ; Fzona < 1200 ; Fzona++)
				{
					ZonaYHoraDadaKeyConTrim key = new ZonaYHoraDadaKeyConTrim(Fhora, Fzona, Ftrim);
					if(hash4C.get(key) == null)
					{
						faltantes[Fzona]++;
					}
				}
			}
		}
		System.out.println("Porcentaje de datos faltantes por zona");
		for(int i: faltantes)
		{
			int num = 1;
			int cantidad=i;
			String s = num + "|";
			while(cantidad!=0)
			{
				s = s+"*";
				cantidad--;
			}System.out.println(s);
			num++;
		}
	}
}
