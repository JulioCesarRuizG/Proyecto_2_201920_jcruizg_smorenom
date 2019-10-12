package model.data_structures;

public class Feature {
	private MultiPolygon[] geometry;
	
	public Feature(MultiPolygon[] pGeometry)
	{
		geometry = pGeometry;
	}
	public MultiPolygon darMultiPolygon(int pos)
	{
		return geometry[pos];
	}
}
