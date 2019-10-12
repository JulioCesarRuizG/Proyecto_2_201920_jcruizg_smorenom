package model.data_structures;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Scope;

public class Properties {

	private int cartodb_id;
	private String scacodigo;
	private int scatipo;
	private String scanombre;
	private double shape_leng;
	private int MOVEMENT_ID;
	private String DISPLAY_NAME;
	
	public Properties(int pid, String pcodigo, int ptipo, String pnombre, double parea, int movement, String pname)
	{
		cartodb_id = pid;
		scacodigo = pcodigo;
		scatipo = ptipo;
		scanombre = pnombre;
		shape_leng = parea;
		MOVEMENT_ID = movement;
		DISPLAY_NAME = pname;
	}
	
	public String darScaCodigo()
	{
		return scacodigo;
	}
}
