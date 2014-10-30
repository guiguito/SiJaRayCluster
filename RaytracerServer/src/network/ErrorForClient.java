package network;

import java.io.Serializable;

/**
 * Message if there is a trouble when the client is connecting to the server.
 * 
 * @author guiguito
 *
 */
public class ErrorForClient implements Serializable {

	private String error;

	public ErrorForClient(String er) {
		error = er;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
