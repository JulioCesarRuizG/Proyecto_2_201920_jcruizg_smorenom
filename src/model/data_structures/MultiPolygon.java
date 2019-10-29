package model.data_structures;

import java.util.List;

public class MultiPolygon {
	private double[][][][] coordinates;


	public MultiPolygon(double[][][][] Ppoligonos)
	{
		coordinates = Ppoligonos;
	}
	
	public double[][][][] darCoordenadas()
	{
		return coordinates;
	}
}
