package model.data_structures;
import java.util.NoSuchElementException;
public class ArbolRN <K extends Comparable<K>, V>{
	 private static final boolean RED   = true;
	    private static final boolean BLACK = false;

	    private NodoArbol root;     
	    private class NodoArbol  {
	        private K key;           
	        private V value;         
	        private NodoArbol left;
			private NodoArbol right;  
	        private boolean color;    
	        private int size;         

	        public NodoArbol(K key, V val, boolean color, int size) {
	            this.key = key;
	            this.value = val;
	            this.color = color;
	            this.size = size;
	        }
	    }

	
	    public ArbolRN() {
	    }
	    private boolean isRed(NodoArbol x) {
	        if (x == null) return false;
	        return x.color == RED;
	    }
	    private int size(NodoArbol x) {
	        if (x == null) return 0;
	        return x.size;
	    } 
	    public int size() {
	        return size(root);
	    }

	    public boolean isEmpty() {
	        return root == null;
	    }

	    public V get(K key) {
	        if (key == null) throw new IllegalArgumentException("argument to get() is null");
	        return get(root, key);
	    }

	    private V get(NodoArbol nodo, K key) {
	        while (nodo != null) {
	            int cmp = key.compareTo(nodo.key);
	            if      (cmp < 0) nodo = nodo.left;
	            else if (cmp > 0) nodo = nodo.right;
	            else              return nodo.value;
	        }
	        return null;
	    }

	
	    public boolean contains(K key) {
	        return get(key) != null;
	    }

	    public void put(K key, V val) {
	        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
	        if (val == null) {
	            delete(key);
	            return;
	        }

	        root = put(root, key, val);
	        root.color = BLACK;
	    }

	    private NodoArbol put(NodoArbol nodo, K key, V val) { 
	        if (nodo == null) 
	        	return new NodoArbol(key, val, RED, 1);

	        int cmp = key.compareTo(nodo.key);
	        if      (cmp < 0) 
	        	nodo.left  = put(nodo.left,  key, val); 
	        else if (cmp > 0)
	        	nodo.right = put(nodo.right, key, val); 
	        else             
	        	nodo.value   = val;

	        if (isRed(nodo.right) && !isRed(nodo.left))     
	        	nodo = rotateLeft(nodo);
	        if (isRed(nodo.left)  &&  isRed(nodo.left.left))
	        	nodo = rotateRight(nodo);
	        if (isRed(nodo.left)  &&  isRed(nodo.right))    
	        	flipColors(nodo);
	        nodo.size = size(nodo.left) + size(nodo.right) + 1;

	        return nodo;
	    }

	   
	    public void deleteMin() {
	        if (isEmpty())
	        	throw new NoSuchElementException("BST underflow");

	        if (!isRed(root.left) && !isRed(root.right))
	            root.color = RED;

	        root = deleteMin(root);
	        if (!isEmpty()) 
	        	root.color = BLACK;
	    }

	    private NodoArbol deleteMin(NodoArbol nodo) { 
	        if (nodo.left == null)
	            return null;

	        if (!isRed(nodo.left) && !isRed(nodo.left.left))
	        	nodo = moveRedLeft(nodo);

	        nodo.left = deleteMin(nodo.left);
	        return balance(nodo);
	    }


	    public void deleteMax() {
	        if (isEmpty()) throw new NoSuchElementException("BST underflow");

	        if (!isRed(root.left) && !isRed(root.right))
	            root.color = RED;

	        root = deleteMax(root);
	        if (!isEmpty()) root.color = BLACK;
	   
	    }

	    private NodoArbol deleteMax(NodoArbol nodo) { 
	        if (isRed(nodo.left))
	        	nodo = rotateRight(nodo);

	        if (nodo.right == null)
	            return null;

	        if (!isRed(nodo.right) && !isRed(nodo.right.left))
	        	nodo = moveRedRight(nodo);

	        nodo.right = deleteMax(nodo.right);

	        return balance(nodo);
	    }


	    public void delete(K key) { 
	        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
	        if (!contains(key)) return;

	        
	        if (!isRed(root.left) && !isRed(root.right))
	            root.color = RED;

	        root = delete(root, key);
	        if (!isEmpty()) root.color = BLACK;
	    }

	    private NodoArbol delete(NodoArbol nodo, K key) { 

	        if (key.compareTo(nodo.key) < 0)  {
	            if (!isRed(nodo.left) && !isRed(nodo.left.left))
	            	nodo = moveRedLeft(nodo);
	            nodo.left = delete(nodo.left, key);
	        }
	        else {
	            if (isRed(nodo.left))
	            	nodo = rotateRight(nodo);
	            if (key.compareTo(nodo.key) == 0 && (nodo.right == null))
	                return null;
	            if (!isRed(nodo.right) && !isRed(nodo.right.left))
	            	nodo = moveRedRight(nodo);
	            if (key.compareTo(nodo.key) == 0) {
	            	NodoArbol minDerecho = min(nodo.right);
	            	nodo.key = minDerecho.key;
	                nodo.value = minDerecho.value;
	                nodo.right = deleteMin(nodo.right);
	            }
	            else 
	            	nodo.right = delete(nodo.right, key);
	        }
	        return balance(nodo);
	    }

	    private NodoArbol rotateRight(NodoArbol nodo) {
	    	NodoArbol hijoIzquierdo = nodo.left;
	    	nodo.left = hijoIzquierdo.right;
	        hijoIzquierdo.right = nodo;
	        hijoIzquierdo.color = hijoIzquierdo.right.color;
	        hijoIzquierdo.right.color = RED;
	        hijoIzquierdo.size = nodo.size;
	        nodo.size = size(nodo.left) + size(nodo.right) + 1;
	        return hijoIzquierdo;
	    }

	    private NodoArbol rotateLeft(NodoArbol h) {
	    	NodoArbol x = h.right;
	        h.right = x.left;
	        x.left = h;
	        x.color = x.left.color;
	        x.left.color = RED;
	        x.size = h.size;
	        h.size = size(h.left) + size(h.right) + 1;
	        return x;
	    }

	   
	    private void flipColors(NodoArbol h) {
	   
	        h.color = !h.color;
	        h.left.color = !h.left.color;
	        h.right.color = !h.right.color;
	    }

	    private NodoArbol moveRedLeft(NodoArbol h) {
	        
	        flipColors(h);
	        if (isRed(h.right.left)) { 
	            h.right = rotateRight(h.right);
	            h = rotateLeft(h);
	            flipColors(h);
	        }
	        return h;
	    }

	    private NodoArbol moveRedRight(NodoArbol h) {
	      
	        flipColors(h);
	        if (isRed(h.left.left)) { 
	            h = rotateRight(h);
	            flipColors(h);
	        }
	        return h;
	    }

	   
	    private NodoArbol balance(NodoArbol h) {
	   

	        if (isRed(h.right))                    
	        	h = rotateLeft(h);
	        if (isRed(h.left) && isRed(h.left.left))
	        	h = rotateRight(h);
	        if (isRed(h.left) && isRed(h.right))
	        	flipColors(h);

	        h.size = size(h.left) + size(h.right) + 1;
	        return h;
	    }


	
	    public int height() {
	        return height(root);
	    }
	    private int height(NodoArbol x) {
	        if (x == null) return -1;
	        return 1 + Math.max(height(x.left), height(x.right));
	    }


	    public K min() {
	        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
	        return min(root).key;
	    } 

	    private NodoArbol min(NodoArbol x) { 
	   
	        if (x.left == null) return x; 
	        else                return min(x.left); 
	    } 

	   
	    public K max() {
	        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
	        return max(root).key;
	    } 

	    private NodoArbol max(NodoArbol x) { 
	        if (x.right == null) return x; 
	        else                 return max(x.right); 
	    } 



	    public K floor(K key) {
	        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
	        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
	        NodoArbol x = floor(root, key);
	        if (x == null) return null;
	        else           return x.key;
	    }    

	    // the largest key in the subtree rooted at x less than or equal to the given key
	    private NodoArbol floor(NodoArbol x, K key) {
	        if (x == null) return null;
	        int cmp = key.compareTo(x.key);
	        if (cmp == 0) return x;
	        if (cmp < 0)  return floor(x.left, key);
	        NodoArbol t = floor(x.right, key);
	        if (t != null) return t; 
	        else           return x;
	    }

	    /**
	     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
	     * @param key the key
	     * @return the smallest key in the symbol table greater than or equal to {@code key}
	     * @throws NoSuchElementException if there is no such key
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public K ceiling(K key) {
	        if (key == null) 
	        	throw new IllegalArgumentException("argument to ceiling() is null");
	        if (isEmpty()) 
	        	throw new NoSuchElementException("calls ceiling() with empty symbol table");
	        NodoArbol x = ceiling(root, key);
	        if (x == null) 
	        	return null;
	        else   
	        	return x.key;  
	    }

	    private NodoArbol ceiling(NodoArbol x, K key) {  
	        if (x == null) return null;
	        int cmp = key.compareTo(x.key);
	        if (cmp == 0) return x;
	        if (cmp > 0)  return ceiling(x.right, key);
	        NodoArbol t = ceiling(x.left, key);
	        if (t != null) return t; 
	        else           return x;
	    }

	
	    public K select(int k) {
	        if (k < 0 || k >= size()) {
	            throw new IllegalArgumentException("argument to select() is invalid: " + k);
	        }
	        NodoArbol x = select(root, k);
	        return x.key;
	    }

	   
	    private NodoArbol select(NodoArbol x, int k) {
	        int t = size(x.left); 
	        if      (t > k) return select(x.left,  k); 
	        else if (t < k) return select(x.right, k-t-1); 
	        else            return x; 
	    } 


	    public int rank(K key) {
	        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
	        return rank(key, root);
	    } 

	  
	    private int rank(K key, NodoArbol x) {
	        if (x == null) 
	        	return 0; 
	        int cmp = key.compareTo(x.key); 
	        if      (cmp < 0) 
	        	return rank(key, x.left); 
	        else if (cmp > 0) 
	        	return 1 + size(x.left) + rank(key, x.right); 
	        else          
	        	return size(x.left); 
	    } 

	
	    public Iterable<K> keys() {
	        if (isEmpty())
	        	return new Queue<Key>;
	        return keys(min(), max());
	    }

	
	    public Iterable<K> keys(K lo, K hi) {
	        if (lo == null) 
	        	throw new IllegalArgumentException("first argument to keys() is null");
	        if (hi == null) 
	        	throw new IllegalArgumentException("second argument to keys() is null");

	        Queue<Key> queue = new Queue<Key>();
	        keys(root, queue, lo, hi);
	        return queue;
	    } 

	    private void keys(NodoArbol x, Queue<Key> queue, K lo, K hi) { 
	        if (x == null) return; 
	        int cmplo = lo.compareTo(x.key); 
	        int cmphi = hi.compareTo(x.key); 
	        if (cmplo < 0) 
	        	keys(x.left, queue, lo, hi); 
	        if (cmplo <= 0 && cmphi >= 0) 
	        	queue.enqueue(x.key); 
	        if (cmphi > 0) 
	        	keys(x.right, queue, lo, hi); 
	    } 


	    public int size(K lo, K hi) {
	        if (lo == null)
	        	throw new IllegalArgumentException("first argument to size() is null");
	        if (hi == null) 
	        	throw new IllegalArgumentException("second argument to size() is null");

	        if (lo.compareTo(hi) > 0) 
	        	return 0;
	        if (contains(hi))
	        	return rank(hi) - rank(lo) + 1;
	        else        
	        	return rank(hi) - rank(lo);
	    }
}
