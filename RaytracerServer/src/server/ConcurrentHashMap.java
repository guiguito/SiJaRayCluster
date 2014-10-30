package server;

import java.util.HashMap;

public class ConcurrentHashMap<ThreadSocket, Object> {

	private HashMap<ThreadSocket, Object> hash;

	public ConcurrentHashMap() {
		hash = new HashMap<ThreadSocket, Object>();
	}

	/**
	 * Add association ThreadSocket -> object.
	 * 
	 * @param th
	 *            connection.
	 * @param pts
	 *            Object
	 */
	public void add(ThreadSocket th, Object pts) {
		synchronized (hash) {
			if (hash != null) {
				hash.put(th, pts);
			}
		}
	}

	/**
	 * remove association ThreadSocket -> object.
	 * 
	 * @param th
	 *            connection.
	 */
	public Object remove(ThreadSocket th) {
		synchronized (hash) {
			return hash.remove(th);
		}
	}

	/**
	 * Get the object associated to a ThreadSocket.
	 * 
	 * @param th
	 *            connection.
	 */
	public Object getValueAndRemoveIt(ThreadSocket th) {
		synchronized (hash) {
			Object pts = hash.get(th);
			hash.remove(th);
			return pts;
		}
	}

	/**
	 * Returns the object associated with the connection if it exists, null
	 * otherwise.
	 * 
	 * @param th
	 *            connection.
	 */
	public Object has(ThreadSocket th) {
		synchronized (hash) {
			return hash.get(th);
		}
	}

	/**
	 * clear the hashmap sent.
	 */
	public void clear() {
		synchronized (hash) {
			hash.clear();
		}
	}

	/**
	 * Get size.
	 * 
	 * @return size.
	 */
	public int size() {
		synchronized (hash) {
			return hash.size();
		}
	}

}
