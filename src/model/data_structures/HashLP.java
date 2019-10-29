package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> {
	private K[] llaves;
	private Queue<V>[] values;
	private int N;
	private int nElementos;

	public HashLP(int capacidadInicial) {

		llaves = (K[]) new Comparable[capacidadInicial];
		values=  new Queue[capacidadInicial];

		N = capacidadInicial;

		nElementos=0;

	}

	public void put(K pKey, V pValue)
	{  boolean existe=false;
	int posicion=hash(pKey);
	K actual= llaves[posicion];
	while(!existe&&actual!=null){

		if(actual.compareTo(pKey)==0){
			existe=true;
			values[posicion].enQueue(pValue);

		}
		posicion=(posicion+1)%N;
		actual=llaves[posicion];
	}
	if(existe){

	}else{
		nElementos++;
		if(nElementos==N-1){

			rehash(2*N);

		}
		int hashKey= hash(pKey);
		while(llaves[hashKey]!=null){
			hashKey=(hashKey+1)%N;
		}
		llaves[hashKey]=pKey;
		values[hashKey]= new Queue<V>(new Node<V>(pValue,null));

	}
	}
	public void putAll(K pKey, Queue<V> pValue)
	{  boolean existe=false;
	int posicion=hash(pKey);
	K actual= llaves[posicion];
	while(!existe&&actual!=null){

		if(actual.compareTo(pKey)==0){
			existe=true;
			values[posicion]=pValue;

		}
		posicion=(posicion+1)%N;
		actual=llaves[posicion];
	}
	if(existe){

	}else{
		if(nElementos==N/2){

			rehash(2*N);

		}
		int hashKey= hash(pKey);
		while(llaves[hashKey]!=null){
			hashKey=(hashKey+1)%N;
		}
		llaves[hashKey]=pKey;
		values[hashKey]=pValue;

	}
	}
	public void putAll2(K pKey, Queue<V> pValue)
	{  boolean existe=false;
	int posicion=hash(pKey);
	K actual= llaves[posicion];
	while(!existe&&actual!=null){

		if(actual.compareTo(pKey)==0){
			existe=true;
			values[posicion]=pValue;

		}
		posicion=(posicion+1)%N;
		actual=llaves[posicion];
	}
	if(existe){

	}else{
		nElementos+=pValue.size();
		if(nElementos==N-1){

			rehash(10*N);

		}
		int hashKey= hash(pKey);
		while(llaves[hashKey]!=null){
			hashKey=(hashKey+1)%N;
		}
		llaves[hashKey]=pKey;
		values[hashKey]=pValue;

	}
	}

	public Queue<V> get(K pKey){
		int pos=hash(pKey);
		K actual = llaves[pos];
		while(actual!=null){

			if(actual.compareTo(pKey)==0)
				return values[pos];
			pos=(pos+1)%N;
			actual = llaves[pos];
		}
		return null;
	}


	public Queue<V> delete(K pKey){
		int pos=hash(pKey);
		K actual = llaves[pos];
		boolean encontrado=false;
		Queue<V> eliminado = null;
		while(actual!=null){

			if(actual.compareTo(pKey)==0&&!encontrado){
				llaves[pos]=null;
				eliminado=values[pos];
				values[pos]=null;
				encontrado =true;
			}
			if(encontrado){
				Queue<V> valor= values[pos];
				llaves[pos]=null;
				putAll(actual,valor);
			}
			pos++;
			if(pos==N)
				pos=0;
			actual = llaves[pos];
		}
		nElementos-=eliminado.size();
		return eliminado;
	}



	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % N;
	}


	public int darNElementos(){
		return nElementos;
	}


	private void rehash(int capacity) {
		HashLP<K, V> temp = new HashLP<K, V>(capacity);
		for (int i = 0; i < nElementos; i++) {
			if (llaves[i] != null) {
				temp.putAll(llaves[i], values[i]);
			}
		}
		llaves = temp.llaves;
		values = temp.values;
		N=temp.N;
	}


	public Iterador<K> Keys(){
		int pos=0;
		K[] auxiliar=(K[]) new Comparable[llaves.length];
		int agregados = 0;
		
		while(pos<llaves.length){
			if(llaves[pos]!=null){
				auxiliar[agregados]=llaves[pos];
				agregados++;
			}
			pos++;
		}
		Iterador<K> it = new Iterador<K>(auxiliar);
		return it;
	}
	
	public int darModulo()
	{
		return N;
	}

	public static void main(String[] args) {
		HashLP<Integer,String> hash=new HashLP<Integer,String>(3);
		hash.put(1,"uno");
		hash.put(2,"uno");
		hash.put(3,"tres");
		hash.put(4,"dos");
		System.out.println(hash.get(1));
		System.out.println(hash.get(4));
		System.out.println(hash.get(3));
		System.out.println(hash.get(2));
		System.out.println(hash.nElementos);

	}

}