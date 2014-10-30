package network;

import java.io.Serializable;

/**
 * Message sent when a connection is coming.
 * 
 * @author guiguito
 */
public class IncomingConnection implements Serializable {

	private String id;
	private String[] scenes;

	public IncomingConnection(String i, String[] sc) {
		id = i;
		scenes = sc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getScenes() {
		return scenes;
	}

	public void setScenes(String[] scenes) {
		this.scenes = scenes;
	}

}
