package network;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Channel {

	private Socket socket;
	private ObjectInput input;
	private ObjectOutput output;

	public Channel(Socket soc) throws IOException {
		socket = soc;
		output = new ObjectOutputStream(soc.getOutputStream());
		input = new ObjectInputStream(soc.getInputStream());
	}

	public ObjectInput getInput() {
		return input;
	}

	public ObjectOutput getOutput() {
		return output;
	}

	public Socket getSocket() {
		return socket;
	}

	/*
	 * public void renewInputStream() throws IOException{ //input.close(); input
	 * = new ObjectInputStream(socket.getInputStream()); }
	 * 
	 * public void renewOutputStream() throws IOException{ //output.close();
	 * output = new ObjectOutputStream(socket.getOutputStream()); }
	 */

}
