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
import model.data_structures.HashLP;
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
	
	private int caragadosColaM1;
	private int caragadosColaS1;
	private int caragadosColaH1;
	private int caragadosColaM2;
	private int caragadosColaS2;
	private int caragadosColaH2;
	private int caragadosColaM3;
	private int caragadosColaS3;
	private int caragadosColaH3;
	private int caragadosColaM4;
	private int caragadosColaS4;
	private int caragadosColaH4;
	

	private boolean valor=true;
	private Viaje agregar;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	// por alguna razón, no carga correctamente el excel que se nos entrega. Pero, si utilizamos uno con el mismo orden y tipo de datos pero que solo tiene mil lineas,funciona correctamente
	public MVCModelo() throws Exception{
		String pRutaM1="";
		String pRutaS1="";
		String pRutaH1="";
		String pRutaM2="";
		String pRutaS2="";
		String pRutaH2="";
		String pRutaM3="";
		String pRutaS3="";
		String pRutaH3="";
		String pRutaM4="";
		String pRutaS4="";
		String pRutaH4="";
		String jsonruta = "";
		String txtruta = "";
		Gson gson = new Gson();
			pRutaM1=".data/bogota-cadastral-2018-1-MonthlyAggregate.csv";
			pRutaH1=".data/bogota-cadastral-2018-1-HourlyAggregate.csv";
			pRutaS1=".data/bogota-cadastral-2018-1-WeeklyAggregate.csv";
			pRutaM2=".data/bogota-cadastral-2018-2-MonthlyAggregate.csv";
			pRutaH2=".data/bogota-cadastral-2018-2-HourlyAggregate.csv";
			pRutaS2=".data/bogota-cadastral-2018-2-WeeklyAggregate.csv";
			pRutaM3=".data/bogota-cadastral-2018-3-MonthlyAggregate.csv";
			pRutaH3=".data/bogota-cadastral-2018-3-HourlyAggregate.csv";
			pRutaS3=".data/bogota-cadastral-2018-3-WeeklyAggregate.csv";
			pRutaM4=".data/bogota-cadastral-2018-4-MonthlyAggregate.csv";
			pRutaH4=".data/bogota-cadastral-2018-4-HourlyAggregate.csv";
			pRutaS4=".data/bogota-cadastral-2018-4-WeeklyAggregate.csv";
			jsonruta = ".data/bogota_cadastral.json";
			txtruta = ".data/asdsadasdas.txt";
		
		CSVReader reader = null;
		CSVReader reader2 = null;
		CSVReader reader3 = null;
		CSVReader reader4 = null;
		CSVReader reader5= null;
		CSVReader reader6 = null;
		CSVReader reader7 = null;
		CSVReader reader8 = null;
		CSVReader reader9 = null;
		JsonReader jsonreader = null;

		try {
			reader= new CSVReader(new FileReader(pRutaM1));
			for(String[] nextLine : reader) {
				if(nextLine.toString().contains("sourceid,dstid,month,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
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

					caragadosColaM1++;
				}
			}
			reader2= new CSVReader(new FileReader(pRutaH1));
			for(String[] nextLine : reader2) {
				if(nextLine.toString().contains("sourceid,dstid,month,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
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

					caragadosColaH1++;
				}
			}
			
			reader3= new CSVReader(new FileReader(pRutaS1));
			for(String[] nextLine : reader3) {
				if(nextLine.toString().contains("sourceid,dstid,month,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
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

					caragadosColaS1++;
				}
			}

			
			jsonreader = new JsonReader(new FileReader(jsonruta));
			Feature[] lista = gson.fromJson(jsonruta, Feature[].class);
			if(lista[0].darMultiPolygon(0).darPropiedades().darScaCodigo().equals("004575"))
			{
				System.out.println("Se cargó el archivo json correctamente");
			}
			
					
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
			if (reader != null) {
				try {
					reader.close();
					reader2.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
