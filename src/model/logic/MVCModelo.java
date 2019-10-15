package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.opencsv.CSVReader;

import model.data_structures.Feature;
import model.data_structures.FeatureCollection;
import model.data_structures.HashLP;
import model.data_structures.JsonCompleto;
import model.data_structures.MaxHeapCP;
import model.data_structures.MultiPolygon;
import model.data_structures.Viaje;

/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */

	private MaxHeapCP heap1;
	private HashLP hash1;

	private int cargados;

	private boolean valor=true;
	private Viaje agregar;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	// por alguna razón, no carga correctamente el excel que se nos entrega. Pero, si utilizamos uno con el mismo orden y tipo de datos pero que solo tiene mil lineas,funciona correctamente
	public MVCModelo(int n) throws Exception{
		
		String jsonruta = "./data/bogota_cadastral.json";
		JsonReader jsonreader = null;
		Gson gson = new Gson();
		jsonreader = new JsonReader(new FileReader(jsonruta));
		FeatureCollection lista = gson.fromJson(jsonruta, FeatureCollection.class);
		
		String pRutaM="";
		String pRutaS="";
		String pRutaH="";
		if(n == 1)
		{
			pRutaM="./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv";
			pRutaH="./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
			pRutaS="./data/bogota-cadastral-2018-1-WeeklyAggregate.csv";
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

		
		String txtruta = "./data/asdsadasdas.txt";

		CSVReader reader1 = null;
		CSVReader reader2 = null;
		CSVReader reader3 = null;
		

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
			if(cargados == 1048575)
			{
				System.out.println("Se ha cargado bien 1 de 5");
			}
			else
			{
				System.out.println("Error al cargar 1 de 5");
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

					Viaje i = new Viaje(inicioID,destinoID,hora,tiempoPromedioEnSegundos,desviacionEstandar,tiempoPromedioGEnSegundos,desviacionEstandarG);
					agregar = i;

					cargados++;

				}
			}

			if(cargados == 1048575)
			{
				System.out.println("Se ha cargado bien 2 de 5");
			}
			else
			{
				System.out.println("Error al cargar 2 de 5");
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
			if(cargados == 1048575)
			{
				System.out.println("Se ha cargado bien 3 de 5");
			}
			else
			{
				System.out.println("Error al cargar 3 de 5");
			}
			cargados = 0;

			
//			if(lista[0].darMultiPolygon(0).darPropiedades().darScaCodigo().equals("004575"))
//			{
//				System.out.println("Se ha cargado bien 4 de 5");
//			}
//			else
//			{
//				System.out.println("Error al cargar 4 de 5");
//			}


			FileReader lector = new FileReader(txtruta);
			BufferedReader leer = new BufferedReader(lector);
			String lineaActual = leer.readLine();
			while(lineaActual != "")
			{
				String[] valores = lineaActual.split(",");
				//				valores[0]
				//				valores[1]//Falta asignar
				//				valores[2]		
				lineaActual = leer.readLine();
			}

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


}
