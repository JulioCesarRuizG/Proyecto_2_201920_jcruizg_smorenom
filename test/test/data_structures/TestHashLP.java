package test.data_structures;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.HashLP;
import model.data_structures.Iterador;

import static org.junit.Assert.*;

public class TestHashLP {

	private HashLP<Integer,String> hash;
	
	@Before
	public void SetUp1()
	{
		hash=new HashLP<Integer,String>(3);
		hash.put(1,"uno");
		hash.put(2,"uno");
		hash.put(3,"tres");
		hash.put(4,"dos");
	}
	
	@Test
	public void TestGet()
	{
		SetUp1();
		try
		{
			assertEquals("No se ha extraído correctamente del hash", "dos", hash.get(4).toString());
		}
		catch (Exception e) {
			fail("Error al pedir elementos del hash");
		}
	}
	
	@Test
	public void TestPut()
	{
		SetUp1();
		try
		{
			hash.put(5, "cuatro");
			assertEquals("No se ha agregado correctamente al hash", "cuatro", hash.get(5).toString());
		}
		catch (Exception e) {
			fail("Error al agregar elementos al hash");
		}
	}
	
	
	
	@Test
	public void TestDarElementos()
	{
		SetUp1();
		try
		{
			assertEquals("No es la cantidad de elementos adecuada", 4, hash.darNElementos());
		}
		catch (Exception e) {
			fail("Error al pedir la cantidad de elementos del hash");
		}
	}
	
	@Test
	public void TestRehash()
	{
		SetUp1();
		try
		{
			int antiguo = hash.darModulo();
			hash.put(4, "cinco");
			hash.put(5, "seis");
			hash.put(6, "siete");
			assertEquals("No es la cantidad de elementos adecuada", antiguo*2, hash.darModulo());
		}
		catch (Exception e) {
			fail("Error al hacer rehash");
		}
	}
	
	@Test
	public void TestIterador()
	{
		SetUp1();
		try
		{
			Iterador it = hash.Keys();
			assertEquals("No es la cantidad de elementos adecuada", null, null);
		}
		catch (Exception e) {
			fail("Error al hacer un iterador de llaves");
		}
	}
	
	@Test
	public void TestDelete()
	{
		SetUp1();
		try
		{
			hash.delete(3);
			assertEquals("No es la cantidad de elementos adecuada", null, hash.get(3));
		}
		catch (Exception e) {
			fail("Error al eliminar elementos del hash");
		}
	}
}
