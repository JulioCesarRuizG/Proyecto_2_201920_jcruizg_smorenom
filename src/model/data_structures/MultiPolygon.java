package model.data_structures;

public class MultiPolygon {
	private Polygon[] coordinates;
	private Properties propiedades;
	
	public MultiPolygon(Polygon[] Ppoligonos, Properties Ppropiedades)
	{
		coordinates = Ppoligonos;
	}
	
	public Properties darPropiedades()
	{
		return propiedades;
	}
}
