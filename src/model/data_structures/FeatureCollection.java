package model.data_structures;

import java.util.List;

public class FeatureCollection {
	
	private Feature[] features;
	
	public FeatureCollection( Feature[] pFeatures)
	{
		features = pFeatures;
	}

	public Feature[] getFeatures() {
		return features;
	}

	public void setFeatures(Feature[] features) {
		this.features = features;
	}
}
