package client;

import java.util.Collections;
import java.util.List;

public class ConcurrentList<Object>{
	
	List<Object> liste;
	
	public ConcurrentList(List<Object> l){
		liste = Collections.synchronizedList(l);
	}	
	
	/**
	 * Add an object.
	 * @param obj
	 */
	public void add(Object obj){
		synchronized (liste) {
			if (obj != null){
				liste.add(obj);
			}
		}			
	}

	/**
	 * Remove an object.
	 * @param obj
	 */
	public void remove(Object obj){
		synchronized (liste){
			liste.remove(obj);
		}
	}
	
	/**
	 * Return true if the object is in the list.
	 * @param obj
	 */
	public boolean contains(Object obj){
		synchronized (liste){
			return liste.contains(obj);
		}
	}
	
	/**
	 * Return the size of the list.
	 * @param obj
	 */
	public int size(){
		synchronized (liste){
			return liste.size();
		}
	}
	
	/**
	 * Get an object and remove it.
	 * @param obj
	 */
	public Object getNextAndRemove(){
		synchronized (liste){
			if(size()>0){
				Object obj = liste.get(0);
				//System.gc();
				liste.remove(0);
				//System.out.println("avant - freememory: "+Runtime.getRuntime().freeMemory()+" totalMemory: "+Runtime.getRuntime().totalMemory()+" maxMemory: "+Runtime.getRuntime().maxMemory());
				//System.gc();
				//System.out.println("après - freememory: "+Runtime.getRuntime().freeMemory()+" totalMemory: "+Runtime.getRuntime().totalMemory()+" maxMemory: "+Runtime.getRuntime().maxMemory());
				return obj;
			}else{
				return null;
			}
		}
	}
	
	/**
	 * Clear the list.
	 */
	public void clear() {
		synchronized (liste){
			liste.clear();
		}
	}
	
	/**
	 * Get reference on the list.
	 * @return reference on the list.
	 */
	public List<Object> getRefOnList(){
		return liste;
	}

	/**
	 * Transfers object from src to this list.
	 * @param src data sources.
	 */
	public void transferData(ConcurrentList<Object> src){
		if (src != this){
			while (src.size()>0){
				liste.add(src.getNextAndRemove());
			}
		}
	}
	
}
