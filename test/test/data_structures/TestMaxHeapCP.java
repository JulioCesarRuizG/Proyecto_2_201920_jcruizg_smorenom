package test.data_structures;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.data_structures.MaxHeapCP;
import model.data_structures.Viaje;
import model.logic.MVCModelo;

public class TestMaxHeapCP {

	private Viaje viaje1;
	private Viaje viaje2;
	private Viaje viaje3;
	private Viaje viaje4;
	private Viaje viaje5;
	private MaxHeapCP heap;

	@Before
	public void setUp1()
	{
		heap = new MaxHeapCP<Viaje>();
		viaje1 = new Viaje(800, 2, 100, 2000, 10, 1000, 400);
		viaje2 = new Viaje(600, 4, 500, 20000, 200, 1020, 700);
		viaje3 = new Viaje(400, 10, 400, 100000, 1000, 800, 1000);
		viaje4 = new Viaje(200, 15, 200, 10000, 500, 700, 600);
		viaje5 = new Viaje(100, 20, 1000, 500, 800, 500, 800);
		heap.agregar(viaje1);
		heap.agregar(viaje2);
		heap.agregar(viaje3);
		heap.agregar(viaje4);
	}

	@Test 
	public void Testagregar()
	{
		setUp1();
		try
		{
			heap.agregar(viaje5);
			Viaje esperadoH = (Viaje) heap.darMinimo();
			assertEquals("No se ha agregado correctamente: heap 10",viaje5, esperadoH);
		}
		catch(Exception e)
		{
			fail("No se ha agregado correctamente: 1000");
		}
	}

	@Test
	public void TestdarNumElementos()
	{
		setUp1();
		try
		{
			assertEquals("No se ha dado la cantidad adecuada: heap",4, heap.darNumElementos());
		}
		catch(Exception e)
		{
			fail("Ha fallado el método darNumElementos: 100");
		}
	}

	@Test 
	public void TestEsVacia()
	{
		setUp1();
		try
		{
			assertEquals("No se ha dado el valor adecuado: heao",false, heap.esVacia() );
		}
		catch(Exception e)
		{
			fail("Ha fallado el método es vacía: 100");
		}
	}

	@Test
	public void TestSacarMax()
	{
		setUp1();
		try
		{
			assertEquals("No se ha sacado el máximo correctamente: heap", viaje3, heap.sacarMax()); 
		}
		catch (Exception e) {
			fail("No se ha sacado el mayor correctamente: 10");
		}
	}

}
